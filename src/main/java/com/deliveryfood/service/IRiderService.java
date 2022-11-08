package com.deliveryfood.service;

import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.vo.RiderRegisterVO;
import com.deliveryfood.vo.RiderUpdateVO;

public interface IRiderService {

    boolean certification(String userId, String code);

    boolean register(RiderRegisterVO registerVO);

    boolean withdraw(String userId, UserRequest userRequest);

    boolean modifyRider(String userId, RiderUpdateVO registerVO);

    void deleteRiderByEmail(String email);
}
