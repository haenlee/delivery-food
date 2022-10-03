package com.deliveryfood.service;

import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.model.OrderInput;
import com.deliveryfood.model.RestaurantInput;

import java.util.List;

public interface OrderService {

    void createOrder(OrderInput orderInput);
    OrderDto findOrder(OrderInput orderInput);
    List<OrderDto> findOrderById(OrderInput orderInput);
    void modifyOrderById(OrderInput orderInput);
}