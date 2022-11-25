package com.deliveryfood.dao;

import com.deliveryfood.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean register(RestaurantDto restaurantDto) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.RestaurantMapper.register", restaurantDto);
        return true;
    }

    public void deleteByRestaurantId(String restaurantId) {
        sqlSessionTemplate.delete("com.deliveryfood.mapper.RestaurantMapper.deleteByRestaurantId", restaurantId);
    }

    public RestaurantDto findUserById(RestaurantDto restaurantDto) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.RestaurantMapper.findUserById", restaurantDto.getRestaurantId());
    }
}
