package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.UserRegisterVO;
import com.deliveryfood.vo.UserUpdateVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    boolean certification(String userId, String code);

    boolean register(UserRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    boolean modifyUser(String userId, UserUpdateVO registerVO);
}
