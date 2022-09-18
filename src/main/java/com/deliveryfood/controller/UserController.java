package com.deliveryfood.controller;

import com.deliveryfood.model.UserInput;
import com.deliveryfood.model.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/certification")
    public void certification(@RequestParam int code)  {
        // 입력한 코드로 본인 인증
    }

    @PostMapping("/signin")
    public void signin(UserInput userInput) {
        // 회원 가입
    }

    @PostMapping("/signout")
    public void signout(UserRequest userRequest) {
        // 회원 탈퇴 (session을 삭제할 뿐 정보의 변경은 없다.)
    }

    @PostMapping("/login")
    public void login(UserRequest userRequest) {
        // 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 로그아웃
    }

    @GetMapping("/{userId}")
    public void findUser(@PathVariable int userId) {
        // 회원 조회
    }

    @PutMapping("/{userId}")
    public void modifyUser(UserInput userInput) {
        // 회원 정보 수정
   }

    @GetMapping("/{userId}/orders")
    public void findOrderByUserId(@PathVariable String userId) {
        // 유저의 모든 주문을 조회
    }
}