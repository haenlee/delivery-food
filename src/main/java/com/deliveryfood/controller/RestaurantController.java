package com.deliveryfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @PostMapping("/certification")
    public void certification() {
        // 가게 회원 가입 인증
    }

    @PostMapping("/signin")
    public void signin() {
        // 가게 회원 가입
    }

    @PostMapping("/signout")
    public void signout() {
        // 가게 회원 탈퇴
    }

    @PostMapping("/login")
    public void login() {
        // 가게 회원 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 가게 회원 로그아웃
    }

    @GetMapping("/{userId}")
    public void findUser(@PathVariable int userId) {
        // 가게 회원 조회
    }

    @PutMapping("/{userId}")
    public void modifyUser(@PathVariable int userId) {
        // 가게 회원 정보 수정
    }
}