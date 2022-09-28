package com.deliveryfood.dao;

import com.deliveryfood.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean register(UserDto user) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.UserMapper.register", user);
        return true;
    }

    public UserDto findById(String email) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.UserMapper.findById", email);
    }

    public boolean withdraw(UserDto user) {
        sqlSessionTemplate.update("com.deliveryfood.mapper.UserMapper.findById", user);
        return true;
    }

    public void updateUser(UserDto userDto) {
        sqlSessionTemplate.update("com.deliveryfood.mapper.UserMapper.updateStatus", userDto);
    }
}
