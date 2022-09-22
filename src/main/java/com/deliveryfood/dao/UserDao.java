package com.deliveryfood.dao;

import com.deliveryfood.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean register(UserDto userInput) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.UserMapper.register", userInput);
        return true;
    }

    public UserDto findById(String email) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.UserMapper.findById", email);
    }

    public boolean withdraw(String userId) {
        sqlSessionTemplate.update("com.deliveryfood.mapper.UserMapper.findById", userId);
        return true;
    }
}
