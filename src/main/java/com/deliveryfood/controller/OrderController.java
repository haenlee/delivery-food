package com.deliveryfood.controller;

import com.deliveryfood.dto.OrderDto;
import com.deliveryfood.model.OrderInput;
import com.deliveryfood.model.OrderMenuInput;
import com.deliveryfood.service.IOrderMenuService;
import com.deliveryfood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final IOrderMenuService orderMenuService;

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



    @GetMapping("/OrderMenu")
    public OrderMenuInput findOrderMenuById(@RequestBody OrderMenuInput orderMenuInput) {
        // 주문메뉴를 조회한다.
        log.info("findOrderMenuById 컨트롤러 호출");
        OrderMenuInput OrderMenu = OrderMenuInput.builder()
                .optionId(orderMenuInput.getOptionId())
                .menuId(orderMenuInput.getMenuId())
                .build();
        return orderMenuService.findOrderMenuById(OrderMenu);
    }
    @PostMapping("/OrderMenu")
    public void createOrderMenuById(@RequestBody OrderMenuInput orderMenuInput) {
        // 주문메뉴를 생성한다.
        log.info("createOrderMenuById 컨트롤러 호출");
        OrderMenuInput OrderMenu = OrderMenuInput.builder()
                .optionId(orderMenuInput.getOptionId())
                .menuId(orderMenuInput.getMenuId())
                .build();
        orderMenuService.createOrderMenuById(OrderMenu);
    }
    @DeleteMapping("/OrderMenu")
    public void deleteOrderMenuById(@RequestBody OrderMenuInput orderMenuInput) {
        // 주문메뉴를 삭제한다.
        log.info("deleteOrderMenuById 컨트롤러 호출");
        OrderMenuInput OrderMenu = OrderMenuInput.builder()
                .optionId(orderMenuInput.getOptionId())
                .menuId(orderMenuInput.getMenuId())
                .build();
        orderMenuService.deleteOrderMenuById(OrderMenu);
    }
}