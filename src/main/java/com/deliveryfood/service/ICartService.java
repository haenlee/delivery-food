package com.deliveryfood.service;

import com.deliveryfood.dao.model.CartMenuDto;
import com.deliveryfood.service.model.CartMenuVO;

import java.util.List;

public interface ICartService {

    List<CartMenuDto> findCart(String userId);

    boolean deleteCart(String userId);

    boolean addMenu(CartMenuVO menuVO);

    boolean deleteMenu(String userId, int index);
}
