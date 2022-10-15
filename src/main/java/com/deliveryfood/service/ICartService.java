package com.deliveryfood.service;

import com.deliveryfood.dto.CartMenuDto;
import com.deliveryfood.vo.CartMenuVO;

import java.util.List;

public interface ICartService {

    List<CartMenuDto> findCart(String userId);

    void deleteCart(String userId);

    void addMenu(CartMenuVO menuVO);

    void deleteMenu(String userId, int index);
}
