package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.RiderRegisterVO;
import com.deliveryfood.vo.RiderUpdateVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IRiderService extends UserDetailsService {

    boolean certification(String userId, String code);

    boolean register(RiderRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    boolean modifyRider(String userId, RiderUpdateVO registerVO);
}
