package com.deliveryfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/riders")
public class RiderController {

    @PostMapping("/certification")
    public void certification() {
        // 라이더 회원 가입 인증
    }

    @PostMapping("/signin")
    public void signin() {
        // 라이더 회원 가입
    }

    @PostMapping("/signout")
    public void signout() {
        // 라이더 회원 탈퇴
    }

    @PostMapping("/login")
    public void login() {
        // 라이더 회원 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 라이더 회원 로그아웃
    }

    @GetMapping("/{userId}")
    public void findUser(@PathVariable String userId) {
        // 라이더 회원 조회
    }

    @PutMapping("/{userId}")
    public void modifyUser(@PathVariable String userId) {
        // 라이더 회원 정보 수정
    }
}