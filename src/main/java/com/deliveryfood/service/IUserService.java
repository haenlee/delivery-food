package com.deliveryfood.service;

import com.deliveryfood.dto.UserDto;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.UserRegisterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    boolean certification(String userId, String code);

    boolean register(UserRegisterVO registerVO);

    boolean withdraw(UserRequest userRequest);

    boolean modifyUser(UserRegisterVO registerVO);

    UserDto findUser(String email);
}
