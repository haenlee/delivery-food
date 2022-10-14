package com.deliveryfood.service;

import com.deliveryfood.Util.MemberSession;
import com.deliveryfood.dao.MemberDao;
import com.deliveryfood.dao.UserDao;
import com.deliveryfood.dto.MemberDto;
import com.deliveryfood.dto.UserDto;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.util.MemberSession;
import com.deliveryfood.vo.UserRegisterVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService extends MemberService implements IUserService {

    private final UserDao userDao;

    public UserService(MemberDao memberDao, UserDao userDao, MemberSession session) {
        super(memberDao);
        this.userDao = userDao;
    }

    @Override
    public boolean certification(String userId, String code) {
        // REGISTER_CODE 와 일치하면 인증 완료
        if(!super.certification(userId, code)) {
            // 멤버 이슈가 있음
            return false;
        }

        UserDto userDto = userDao.findByUserId(userId);
        if(userDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        return true;
    }

    @Override
    public boolean register(UserRegisterVO registerVO) {
        String uuid = UUID.randomUUID().toString();
        if(!super.register(registerVO, uuid, MemberDto.Role.ROLE_USER)) {
            // 멤버 이슈가 있음
            return false;
        }

        if(userDao.findByUserId(uuid) != null) {
            // 중복 유저 존재
            return false;
        }

        UserDto userDto = UserDto.builder()
                .userId(uuid)
                .address(registerVO.getAddress())
                .nickname(registerVO.getNickname())
                .grade(UserDto.Grade.COMMON)
                .imagePath(registerVO.getImagePath())
                .regDt(LocalDateTime.now())
                .build();

        userDao.register(userDto);
        return true;
    }

    @Override
    public boolean withdraw(UserRequest userRequest) {
        if(!super.withdraw(userRequest)) {
            // 멤버 이슈가 있음
            return false;
        }

        UserDto userDto = userDao.findByEmail(userRequest.getEmail());
        if(userDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        return true;
    }

    @Override
    public boolean modifyUser(UserRegisterVO registerVO) {
        UserDto userDto = userDao.findByEmail(registerVO.getEmail());
        if(userDto == null) {
            // 유저가 존재하지 않음
            return false;
        }

        userDto.setAddress(registerVO.getAddress());
        userDto.setNickname(registerVO.getNickname());
        userDto.setImagePath(registerVO.getImagePath());
        userDao.updateUser(userDto);
        return true;
    }

    @Override
    public UserDto findUser(String email) {
        UserDto userDto = userDao.findByEmail(email);
        if(userDto == null) {
            // 유저가 존재하지 않음
            return null;
        }

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return super.loadUserByUsername(username);
    }
}
