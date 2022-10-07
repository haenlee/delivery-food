package com.deliveryfood.controller;

import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.model.OrderInput;
import com.deliveryfood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public void createOrder() {
        // 주문 생성
        OrderInput order = OrderInput.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .userId(String.valueOf(UUID.randomUUID()))
                .state("0")
                .build();
        orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderDto findOrder(@PathVariable String orderId) {
        // 주문 조회
        OrderInput order = OrderInput.builder()
                .orderId(orderId)
                .build();
        return orderService.findOrder(order);
    }

    @GetMapping("/{userId}/orders")
    public List<OrderDto> findOrderById(@PathVariable String userId) {
        // 유저의 모든 주문을 조회한다.
        OrderInput order = OrderInput.builder()
                .userId(userId)
                .build();
        return orderService.findOrderById(order);
    }

    @PutMapping("/{orderId}")
    public void modifyOrderById(@PathVariable String orderId
            , @RequestBody OrderInput orderInput) {
        // 주문 수정
        OrderInput order = OrderInput.builder()
                .orderId(orderId)
                .state(orderInput.getState())
                .build();
        orderService.modifyOrderById(order);
    }
}