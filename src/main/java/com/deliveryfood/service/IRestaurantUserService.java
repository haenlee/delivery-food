package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.RestaurantUserRegisterVO;

public interface IRestaurantUserService {

    boolean certification(String username, String code);

    boolean register(RestaurantUserRegisterVO registerVO);

    boolean withdraw(UserRequest userRequest);
}
