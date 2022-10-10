package com.deliveryfood.controller;

import com.deliveryfood.model.LoginResult;
import com.deliveryfood.model.RiderInput;
import com.deliveryfood.model.UserRequest;
import com.deliveryfood.service.IRiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
public class RiderController {

    private final IRiderService riderService;

    @PostMapping("/certification")
    public void certification(@RequestParam String code) {
        // 입력한 코드로 본인 인증
        riderService.certification(code);
    }

    @PostMapping("/register")
    public void register(@RequestBody RiderInput riderInput) {
        // 라이더 회원 가입
        riderService.register(riderInput);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody UserRequest userRequest) {
        // 라이더 회원 탈퇴 (session을 삭제할 뿐 정보의 변경은 없다.)
        riderService.withdraw(userRequest);
    }

    @PostMapping("/login")
    public HttpStatus login(HttpServletRequest request) {
        // 로그인
        UserRequest userRequest = new UserRequest(request.getParameter("username"), request.getParameter("password"));
        LoginResult result = riderService.login(userRequest);
        if(result.equals(LoginResult.NOT_REGISTER_AUTH)) {
            return HttpStatus.FOUND;
        }

        return HttpStatus.OK;
    }

    @PostMapping("/logout")
    public void logout() {
        // 라이더 회원 로그아웃
    }

    @PutMapping("/{userId}")
    public void modifyUser(@RequestBody RiderInput riderInput) {
        // 라이더 회원 정보 수정 (현재는 커미션만 수정 가능)
        riderService.modifyRider(riderInput);
    }
}