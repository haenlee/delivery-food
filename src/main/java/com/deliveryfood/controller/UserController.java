package com.deliveryfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @PostMapping("/certification")
    public void certification() {
        // 회원 가입 인증
    }

    @PostMapping("/signin")
    public void signin() {
        // 회원 가입
    }

    @PostMapping("/signout")
    public void signout() {
        // 회원 탈퇴
    }

    @PostMapping("/login")
    public void login() {
        // 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 로그아웃
    }

    @GetMapping("/{userId}")
    public void findUser(@PathVariable String userId) {
        // 회원 조회
    }

    @PutMapping("/{userId}")
    public void modifyUser(@PathVariable String userId) {
        // 회원 정보 수정
   }
}