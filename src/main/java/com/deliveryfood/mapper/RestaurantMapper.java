package com.deliveryfood.mapper;


import com.deliveryfood.dao.model.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantMapper {

    boolean register(RestaurantDto restaurantDto);
    void deleteByRestaurantId(RestaurantDto restaurantDto);
    RestaurantDto findUserById(RestaurantDto restaurantDto);
}