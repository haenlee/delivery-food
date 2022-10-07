package com.deliveryfood.service;

import com.deliveryfood.model.LoginResult;
import com.deliveryfood.model.RiderInput;
import com.deliveryfood.model.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IRiderService extends UserDetailsService {

    boolean certification(String code);

    boolean register(RiderInput riderInput);

    boolean withdraw(UserRequest userRequest);

    LoginResult login(UserRequest userRequest);

    boolean modifyRider(RiderInput riderInput);
}
