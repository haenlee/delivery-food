package com.deliveryfood.service;

import com.deliveryfood.dao.model.RestaurantDto;
import com.deliveryfood.service.model.RestaurantRegisterVO;

public interface IRestaurantService {

    boolean register(RestaurantRegisterVO restaurantRegisterVO);

    void deleteByRestaurantId(String restaurantId);

    RestaurantDto findUserById(RestaurantRegisterVO restaurantRegisterVO);
}