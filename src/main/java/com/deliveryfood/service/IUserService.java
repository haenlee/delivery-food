package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.UserRegisterVO;
import com.deliveryfood.vo.UserUpdateVO;

public interface IUserService {

    boolean certification(String userId, String code);

    boolean register(UserRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    boolean modifyUser(String userId, UserUpdateVO registerVO);
}
