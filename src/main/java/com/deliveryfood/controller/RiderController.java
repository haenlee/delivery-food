package com.deliveryfood.controller;

import com.deliveryfood.controller.model.request.RiderRegisterRequest;
import com.deliveryfood.controller.model.request.RiderUpdateRequest;
import com.deliveryfood.controller.model.request.UserRequest;
import com.deliveryfood.service.IRiderService;
import com.deliveryfood.service.impl.MemberService.CertificationResult;
import com.deliveryfood.service.model.RiderRegisterVO;
import com.deliveryfood.service.model.RiderUpdateVO;
import com.deliveryfood.util.SecurityPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
public class RiderController {

    private final IRiderService riderService;

    @PostMapping("/certification")
    public ResponseEntity<CertificationResult> certification(@RequestParam String code) {
        // 입력한 코드로 본인 인증
        String userId = SecurityPrincipal.getLoginUserId();
        CertificationResult result = riderService.certification(userId, code);

        HttpStatus status = HttpStatus.OK;
        if(result != CertificationResult.SUCEESS) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/register")
    public void register(@RequestBody RiderRegisterRequest registerRequest) {
        // 라이더 회원 가입
        RiderRegisterVO registerVO = RiderRegisterVO.convert(registerRequest);
        riderService.register(registerVO);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody UserRequest userRequest) {
        // 라이더 회원 탈퇴 (session을 삭제할 뿐 정보의 변경은 없다.)
        String userId = SecurityPrincipal.getLoginUserId();
        riderService.withdraw(userId, userRequest);
    }

    @PutMapping("/modifyRider")
    public void modifyRider(@RequestBody RiderUpdateRequest updateRequest) {
        // 라이더 회원 정보 수정 (현재는 커미션만 수정 가능)
        String userId = SecurityPrincipal.getLoginUserId();
        RiderUpdateVO updateVO = RiderUpdateVO.convert(updateRequest);
        riderService.modifyRider(userId, updateVO);
    }
}