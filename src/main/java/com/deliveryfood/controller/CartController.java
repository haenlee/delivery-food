package com.deliveryfood.controller;

import com.deliveryfood.model.CartInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartController {

    @GetMapping("/{userId}")
    public void findCart(@PathVariable int userId) {
        // 장바구니 조회
    }

    @DeleteMapping("/{userId}")
    public void deleteCart(@PathVariable int userId) {
        // 장바구니 삭제
    }

    @PostMapping("/add")
    public void addMenu(CartInput cartInput) {
        // 장바구니 메뉴 추가
    }

    @PostMapping("/sub/{menuId}")
    public void deleteMenu(@PathVariable int menuId) {
        // 장바구니 메뉴 삭제
    }
}
