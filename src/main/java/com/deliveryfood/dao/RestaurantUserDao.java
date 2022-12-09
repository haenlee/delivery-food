package com.deliveryfood.dao;

import com.deliveryfood.dao.model.RestaurantUserDto;
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

    public RestaurantUserDto findByUserId(String userId) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.RestaurantUserMapper.findByUserId", userId);
    }

    public void deleteRestaurantUserByUserId(String userId) {
        sqlSessionTemplate.delete("com.deliveryfood.mapper.RestaurantUserMapper.deleteRestaurantUserByUserId", userId);
    }
}
