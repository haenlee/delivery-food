package com.deliveryfood.controller;

import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/riders")
public class RiderController {

    @PostMapping("/certification")
    public void certification(@RequestParam int code) {
        // 입력한 코드로 본인 인증
    }

    @PostMapping("/register")
    public void register(UserInput userInput) {
        // 라이더 회원 가입
    }

    @PostMapping("/withdraw")
    public void withdraw(UserRequest userRequest) {
        // 라이더 회원 탈퇴 (session을 삭제할 뿐 정보의 변경은 없다.)
    }

    @PostMapping("/login")
    public void login(UserRequest userRequest) {
        // 라이더 회원 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 라이더 회원 로그아웃
    }

    @GetMapping("/{userId}")
    public void findUser(@PathVariable int userId) {
        // 라이더 회원 조회
    }

    @PutMapping("/{userId}")
    public void modifyUser(@PathVariable int userId) {
        // 라이더 회원 정보 수정
    }
}