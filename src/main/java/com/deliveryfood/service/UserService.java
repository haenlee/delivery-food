package com.deliveryfood.service;

import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;

public interface UserService {

    boolean register(UserInput userInput);

    boolean withdraw(UserRequest userRequest);

    boolean login(UserRequest userInput);
}
