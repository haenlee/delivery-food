package com.deliveryfood.service;

import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.model.CustomUserDetails;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.MemberRegisterVO;
import com.deliveryfood.vo.UserRegisterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberDao memberDao;

    public static final String REGISTER_CODE = "FLAB";

    public boolean certification(String userId, String code) {
        if(!code.equals(REGISTER_CODE)) {
            // 인증 코드가 다름
            return false;
        }

        MemberDto memberDto = memberDao.findByUserId(userId);
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        memberDto.certificateRole();
        memberDao.updateRole(memberDto);
        return true;
    }

    public boolean register(MemberRegisterVO registerVO, String uuid, MemberDto.Role role) {
        MemberDto memberDto = memberDao.findByEmail(registerVO.getEmail());
        if(memberDto != null && memberDto.isExistRole(role)) {
            // 중복 유저 존재
            return false;
        }

        // 비밀번호 암호화
        String hashPw = BCrypt.hashpw(registerVO.getPassword(), BCrypt.gensalt());

        if(memberDto == null) {
            memberDto = MemberDto.builder()
                    .userId(uuid)
                    .name(registerVO.getName())
                    .email(registerVO.getEmail())
                    .password(hashPw)
                    .phone(registerVO.getPhone())
                    .status(MemberDto.Status.REGISTER)
                    .regDt(LocalDateTime.now())
                    .build();
        }

        memberDto.registerRole(role);
        memberDao.register(memberDto);
        return true;
    }

    public boolean withdraw(UserRequest userRequest) {
        MemberDto memberDto = memberDao.findByEmail(userRequest.getEmail());
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        if(!BCrypt.checkpw(userRequest.getPassword(), memberDto.getPassword())) {
            // 비밀번호가 다름
            return false;
        }

        memberDto.setStatus(MemberDto.Status.WITHDRAW);
        memberDao.updateStatus(memberDto);
        return true;
    }

    public boolean modifyUser(UserRegisterVO registerVO) {
        MemberDto memberDto = memberDao.findByEmail(registerVO.getEmail());
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        memberDto.setPhone(registerVO.getPhone());
        memberDao.updateUser(memberDto);
        return true;
    }

    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memberDto = memberDao.findByEmail(username);
        if(memberDto == null) {
            throw new UsernameNotFoundException("Member DB에 member가 존재하지 않음 : " + username);
        }

        CustomUserDetails userDetails = CustomUserDetails.builder()
                .userId(memberDto.getUserId())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .status(memberDto.getStatus())
                .authority(memberDto.getRole().name())
                .build();

        return userDetails;
    }
}
