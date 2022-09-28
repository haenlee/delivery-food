package com.deliveryfood.service;

import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean register(UserInput userInput);

    boolean withdraw(UserRequest userRequest);

    boolean login(UserRequest userInput);
}
