package com.deliveryfood.service;

import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.vo.RestaurantRegisterVO;

import java.util.List;

public interface IRestaurantService {

    void signin(RestaurantRegisterVO registerVO);
    void signout(RestaurantRegisterVO registerVO);
    List<RestaurantDto> findUsers();
    RestaurantDto findUserById(RestaurantRegisterVO registerVO);
    void modifyUserById(RestaurantRegisterVO registerVO);
}