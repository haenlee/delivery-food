package com.deliveryfood.controller;

import com.deliveryfood.dto.CartMenuDto;
import com.deliveryfood.model.CustomUserDetails;
import com.deliveryfood.model.request.CartMenuRequest;
import com.deliveryfood.model.response.CartMenuResponse;
import com.deliveryfood.service.CartService;
import com.deliveryfood.vo.CartMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartMenuResponse>> findCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 장바구니 조회
        List<CartMenuDto> menuList = cartService.findCart(userDetails.getUserId());
        return new ResponseEntity<>(menuList.stream()
                .map(CartMenuResponse::convert)
                .collect(Collectors.toList())
                , HttpStatus.OK);

    }

    @DeleteMapping("/carts")
    public void deleteCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 장바구니 삭제
        cartService.deleteCart(userDetails.getUserId());
    }

    @PostMapping("/add")
    public void addMenu(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CartMenuRequest menuRequest) {
        // 장바구니 메뉴 추가
        cartService.addMenu(CartMenuVO.convert(userDetails.getUserId(), menuRequest));
    }

    @PostMapping("/delete/{index}")
    public void deleteMenu(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable int index) {
        // 장바구니 메뉴 삭제
        cartService.deleteMenu(userDetails.getUserId(), index);
    }
}
