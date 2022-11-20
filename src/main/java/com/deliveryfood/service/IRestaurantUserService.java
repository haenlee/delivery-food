package com.deliveryfood.service;

import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.service.impl.MemberService.CertificationResult;
import com.deliveryfood.service.model.RestaurantUserRegisterVO;

public interface IRestaurantUserService {

    CertificationResult certification(String userId, String code);

    boolean register(RestaurantUserRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    void deleteRestaurantUserByEmail(String email);
}
