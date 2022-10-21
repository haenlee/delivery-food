package com.deliveryfood.mapper;


import com.deliveryfood.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    void signin(RestaurantDto restaurantDto);
    void signout(RestaurantDto restaurantDto);
    List<RestaurantDto> findUsers();
    RestaurantDto findUserById(RestaurantDto restaurantDto);
    void modifyUserById(RestaurantDto restaurantDto);
}