package com.deliveryfood.mapper;

import com.deliveryfood.dao.model.RestaurantUserDto;
import com.deliveryfood.dao.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantUserMapper {

    void register(RestaurantUserDto restaurantUserDto);

    UserDto findByUserId(String userId);

    void deleteRestaurantUserByUserId(String userId);
}
