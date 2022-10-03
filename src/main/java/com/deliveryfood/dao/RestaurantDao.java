package com.deliveryfood.dao;

import com.deliveryfood.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void signin(RestaurantDto restaurantDto) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.RestaurantMapper.signin", restaurantDto);
    }

    public List<RestaurantDto> findUsers() {
        return sqlSessionTemplate.selectList("com.deliveryfood.mapper.RestaurantMapper.findUsers");
    }

    public RestaurantDto findUserById(RestaurantDto restaurantDto) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.RestaurantMapper.findUserById", restaurantDto.getRestaurantId());
    }

    public void modifyUserById(RestaurantDto restaurantDto) {
        sqlSessionTemplate.update("com.deliveryfood.mapper.RestaurantMapper.modifyUserById", restaurantDto);
    }
}
