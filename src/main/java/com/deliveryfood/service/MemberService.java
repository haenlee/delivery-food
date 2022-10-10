package com.deliveryfood.service;

import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.model.CustomUserDetails;
import com.deliveryfood.model.MemberInput;
import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;
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

    public boolean register(MemberInput memberInput, String uuid) {
        if(memberDao.findByEmail(memberInput.getEmail()) != null) {
            // 중복 유저 존재
            return false;
        }

        // 비밀번호 암호화
        String hashPw = BCrypt.hashpw(memberInput.getPassword(), BCrypt.gensalt());

        MemberDto memberDto = MemberDto.builder()
                .userId(uuid)
                .name(memberInput.getName())
                .email(memberInput.getEmail())
                .password(hashPw)
                .phone(memberInput.getPhone())
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

    public boolean modifyUser(UserInput userInput) {
        MemberDto memberDto = memberDao.findByEmail(userInput.getEmail());
        if(memberDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        memberDto.setPhone(userInput.getPhone());
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
