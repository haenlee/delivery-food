package com.deliveryfood.service;

import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.mapper.OrderMapper;
import com.deliveryfood.model.OrderInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public void createOrder(OrderInput orderInput) {
        OrderDto orderDto = OrderDto.builder()
                .orderId(orderInput.getOrderId())
                .userId(orderInput.getUserId())
                .state(orderInput.getState())
                .build();
        orderMapper.createOrder(orderDto);
    }

    @Override
    public OrderDto findOrder(OrderInput orderInput) {
        OrderDto orderDto = OrderDto.builder()
                .orderId(orderInput.getOrderId())
                .build();
        return orderMapper.findOrder(orderDto);
    }

    @Override
    public List<OrderDto> findOrderById(OrderInput orderInput) {
        OrderDto orderDto = OrderDto.builder()
                .userId(orderInput.getUserId())
                .build();
        return orderMapper.findOrderById(orderDto);
    }

    @Override
    public void modifyOrderById(OrderInput orderInput) {
        OrderDto orderDto = OrderDto.builder()
                .orderId(orderInput.getOrderId())
                .state(orderInput.getState())
                .build();
        orderMapper.modifyOrderById(orderDto);
    }
}
