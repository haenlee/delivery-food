package com.deliveryfood.service;

import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;

public interface UserService {

    boolean certification(UserRequest userRequest, String code);

    boolean register(UserInput userInput);

    boolean withdraw(UserRequest userRequest);

    boolean login(UserRequest userRequest);
}
