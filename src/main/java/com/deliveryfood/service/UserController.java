package com.deliveryfood.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{userId}/orders")
    public void findOrderByUserId(@PathVariable String userId) {
        // 유저의 모든 주문을 조회
    }
}
