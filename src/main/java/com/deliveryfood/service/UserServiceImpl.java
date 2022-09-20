package com.deliveryfood.service;

import com.deliveryfood.dto.UserDto;
import com.deliveryfood.mapper.UserMapper;
import com.deliveryfood.model.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

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
                .status(UserDto.STATUS_REGISTER)
                .regDt(LocalDateTime.now())
                .build();

        userMapper.register(user);

        return true;
    }
}
