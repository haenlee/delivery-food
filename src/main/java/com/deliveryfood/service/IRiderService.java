package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.RiderRegisterVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IRiderService extends UserDetailsService {

    boolean certification(String username, String code);

    boolean register(RiderRegisterVO registerVO);

    boolean withdraw(UserRequest userRequest);

    boolean modifyRider(RiderRegisterVO registerVO);
}
