package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.RestaurantUserRegisterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IRestaurantUserService extends UserDetailsService {

    boolean certification(String username, String code);

    boolean register(RestaurantUserRegisterVO registerVO);

    boolean withdraw(UserRequest userRequest);
}
