package com.deliveryfood.service;

import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.service.impl.MemberService.CertificationResult;
import com.deliveryfood.service.model.UserRegisterVO;
import com.deliveryfood.service.model.UserUpdateVO;

public interface IUserService {

    CertificationResult certification(String userId, String code);

    boolean register(UserRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    boolean modifyUser(String userId, UserUpdateVO registerVO);

    String deleteUserByEmail(String email);
}
