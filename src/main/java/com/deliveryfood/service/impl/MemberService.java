package com.deliveryfood.service.impl;

import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.security.CustomUserDetails;
import com.deliveryfood.service.IMemberService;
import com.deliveryfood.service.model.MemberRegisterVO;
import com.deliveryfood.service.model.MemberUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements IMemberService {

    private final MemberDao memberDao;

    public static final String REGISTER_CODE = "FLAB";

    @Override
    public boolean certification(String userId, String code) {
        MemberDto memberDto = memberDao.findByUserId(userId);
        if(memberDto == null) {
            throw new UsernameNotFoundException("Member DB에 member가 존재하지 않음 : " + userId);
        }

        if(memberDto.isExistRole(MemberDto.Role.ROLE_AUTH)) {
            throw new RuntimeException("이미 인증된 유저 : " + userId);
        }

        if(!code.equals(REGISTER_CODE)) {
            log.info("인증 코드 값이 다름 : " + code);
            return false;
        }

        memberDto.certificateRole();
        memberDao.updateRole(memberDto);
        return true;
    }

    @Override
    public boolean register(MemberRegisterVO registerVO, String uuid, MemberDto.Role role) {
        MemberDto findMemberDto = memberDao.findByEmail(registerVO.getEmail());
        if(findMemberDto != null && findMemberDto.isExistRole(role)) {
            throw new RuntimeException("Member DB에 중복 유저가 존재함");
        }

        // 비밀번호 암호화
        String hashPw = BCrypt.hashpw(registerVO.getPassword(), BCrypt.gensalt());
        MemberDto registerMemberDto = MemberDto.builder()
                .userId(uuid)
                .name(registerVO.getName())
                .email(registerVO.getEmail())
                .password(hashPw)
                .phone(registerVO.getPhone())
                .status(MemberDto.Status.REGISTER)
                .role(MemberDto.Role.ROLE_NOT_AUTH.name())
                .regDt(LocalDateTime.now())
                .udtDt(LocalDateTime.now())
                .build();

        registerMemberDto.registerRole(role);
        memberDao.register(registerMemberDto);
        return true;
    }

    @Override
    public boolean withdraw(UserRequest userRequest) {
        MemberDto memberDto = findMemberByEmail(userRequest.getEmail());

        if(!BCrypt.checkpw(userRequest.getPassword(), memberDto.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않음");
        }

        memberDto.setStatus(MemberDto.Status.WITHDRAW);
        memberDao.updateStatus(memberDto);
        return true;
    }

    @Override
    public MemberDto findMemberByEmail(String email) {
        MemberDto memberDto = memberDao.findByEmail(email);
        if(memberDto == null) {
            throw new UsernameNotFoundException("Member DB에 member가 존재하지 않음 : " + email);
        }
        return memberDto;
    }

    @Override
    public String getUserIdByEmail(String email) {
        MemberDto memberDto = findMemberByEmail(email);
        return memberDto.getUserId();
    }

    @Override
    public boolean modifyMember(String userId, MemberUpdateVO updateVO) {
        MemberDto memberDto = memberDao.findByUserId(userId);
        if(memberDto == null) {
            throw new UsernameNotFoundException("Member DB에 member가 존재하지 않음 : " + userId);
        }

        memberDto.setPhone(updateVO.getPhone());
        memberDao.updateMember(memberDto);
        return true;
    }

    @Override
    public void deleteMemberByUserId(String userId) {
        MemberDto memberDto = memberDao.findByUserId(userId);
        if(memberDto == null) {
            throw new UsernameNotFoundException("Member DB에 member가 존재하지 않음 : " + userId);
        }
        memberDao.deleteMemberByUserId(userId);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memberDto = findMemberByEmail(username);

        CustomUserDetails userDetails = CustomUserDetails.builder()
                .userId(memberDto.getUserId())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .status(memberDto.getStatus())
                .authority(memberDto.getRole())
                .build();

        return userDetails;
    }
}
