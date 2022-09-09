package com.deliveryfood.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/checkout")
    public void createOrder() {
        // 주문 생성
    }
    @GetMapping("/{orderId}")
    public void findOrder(@PathVariable String orderId) {
        // 주문 조회
    }
    @PutMapping("/{orderId}")
    public void modifyOrder(@PathVariable String orderId) {
        // 주문 수정
    }
}