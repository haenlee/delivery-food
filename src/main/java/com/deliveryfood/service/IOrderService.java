package com.deliveryfood.service;

import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.model.OrderInput;

import java.util.List;

public interface IOrderService {

    void createOrder(OrderInput orderInput);
    OrderDto findOrder(OrderInput orderInput);
    List<OrderDto> findOrderById(OrderInput orderInput);
    void modifyOrderById(OrderInput orderInput);
}