package com.deliveryfood.dao;

import com.deliveryfood.dto.RestaurantUserDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantUserDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean register(RestaurantUserDto restaurantUserDto) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.RestaurantUserMapper.register", restaurantUserDto);
        return true;
    }

    public RestaurantUserDto findByEmail(String email) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.RestaurantUserMapper.findByEmail", email);
    }

    public RestaurantUserDto findByUserId(String userId) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.RestaurantUserMapper.findByUserId", userId);
    }
}
