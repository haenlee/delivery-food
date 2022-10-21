package com.deliveryfood.service;

import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.model.RestaurantInput;

import java.util.List;

public interface IRestaurantService {

    void signin(RestaurantInput restaurantInput);
    void signout(RestaurantInput restaurantInput);
    List<RestaurantDto> findUsers();
    RestaurantDto findUserById(RestaurantInput restaurantInput);
    void modifyUserById(RestaurantInput restaurantInput);
}