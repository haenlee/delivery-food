package com.deliveryfood.service;

import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.model.CustomUserDetails;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.MemberRegisterVO;
import com.deliveryfood.vo.UserRegisterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberDao memberDao;

    public static final String REGISTER_CODE = "FLAB";

    public boolean certification(String username, String code, MemberDto.Role role) {
        if(!code.equals(REGISTER_CODE)) {
            // 인증 코드가 다름
            return false;
        }

        MemberDto memberDto = memberDao.findByUserId(username);
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        memberDto.setRole(role);
        memberDao.updateRole(memberDto);
        return true;
    }

    public boolean register(MemberRegisterVO registerVO, String uuid) {
        if(memberDao.findByEmail(registerVO.getEmail()) != null) {
            // 중복 유저 존재
            return false;
        }

        // 비밀번호 암호화
        String hashPw = BCrypt.hashpw(registerVO.getPassword(), BCrypt.gensalt());

        MemberDto memberDto = MemberDto.builder()
                .userId(uuid)
                .name(registerVO.getName())
                .email(registerVO.getEmail())
                .password(hashPw)
                .phone(registerVO.getPhone())
                .status(MemberDto.Status.REGISTER)
                .role(MemberDto.Role.ROLE_NOT_AUTH)
                .regDt(LocalDateTime.now())
                .build();

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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memberDto = memberDao.findByEmail(username);
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return null;
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setEmail(memberDto.getEmail());
        userDetails.setPassword(memberDto.getPassword());
        userDetails.setStatus(memberDto.getStatus());
        userDetails.setAuthority(memberDto.getRole().name());
        return userDetails;
    }
}
