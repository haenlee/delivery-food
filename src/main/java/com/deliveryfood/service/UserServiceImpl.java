package com.deliveryfood.service;

import com.deliveryfood.dao.UserDao;
import com.deliveryfood.dto.UserDto;
import com.deliveryfood.mapper.UserMapper;
import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserDao userDao;

    @Override
    public boolean register(UserInput userInput) {
        // 유저 중복 체크 추가
        // 비밀번호 암호화 추가

        UserDto user = UserDto.builder()
                .userId(UUID.randomUUID().toString())
                .name(userInput.getName())
                .email(userInput.getEmail())
                .password(userInput.getPassword())
                .phone(userInput.getPhone())
                .address(userInput.getAddress())
                .status(UserDto.Status.REGISTER)
                .regDt(LocalDateTime.now())
                .build();

        userDao.register(user);

        return true;
    }

    @Override
    public boolean withdraw(UserRequest userRequest) {
        UserDto user = userDao.findById(userRequest.getEmail());
        if(user == null) {
            // 유저가 존재하지 않음
            return false;
        }

        if(!user.getPassword().equals(userRequest.getPassword())) {
            // 비밀번호가 다름
            return false;
        }

        user.setStatus(UserDto.Status.WITHDRAW);
        userDao.withdraw(user);
        return true;
    }

    @Override
    public boolean login(UserRequest userRequest) {
        UserDto user = userDao.findById(userRequest.getEmail());
        if(user == null) {
            // 유저가 존재하지 않음
            return false;
        }

        if(!user.getPassword().equals(userRequest.getPassword())) {
            // 비밀번호가 다름
            return false;
        }
        return true;
    }


}
