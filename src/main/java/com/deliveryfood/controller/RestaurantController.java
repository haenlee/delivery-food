package com.deliveryfood.controller;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.model.MenuInput;
import com.deliveryfood.model.OptionInput;
import com.deliveryfood.model.SubOptionInput;
import com.deliveryfood.model.request.RestaurantRegisterRequest;
import com.deliveryfood.model.request.UserRequest;
import com.deliveryfood.service.IMenuService;
import com.deliveryfood.service.IOptionService;
import com.deliveryfood.service.IRestaurantService;
import com.deliveryfood.service.ISubOptionService;
import com.deliveryfood.vo.RestaurantRegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final IRestaurantService restaurantService;
    private final IMenuService menuService;
    private final IOptionService optionService;
    private final ISubOptionService subOptionService;

    @PostMapping("/certification")
    public void certification(@RequestParam int code) {
        // 입력한 코드로 본인 인증
    }

    @PostMapping("/register")
    public void register(RestaurantRegisterRequest registerRequest) {
        // 가게 회원 가입
    }

    @PostMapping("/withdraw")
    public void withdraw(UserRequest userRequest) {
        // 가게 회원 탈퇴 (session을 삭제할 뿐 정보의 변경은 없다.)
    }

    @PostMapping("/login")
    public void login(UserRequest userRequest) {
        // 가게 회원 로그인
    }

    @PostMapping("/logout")
    public void logout() {
        // 가게 회원 로그아웃
    }

    //TODO : 정책상 제공하지 않으므로 추후 삭제
    @GetMapping("")
    public List<RestaurantDto> findUsers() {
        // 가게 회원 일괄 조회
        return restaurantService.findUsers();
    }
    
    @GetMapping("/{restaurantId}")
    public RestaurantDto findUserById(@PathVariable String restaurantId) {
        // 가게 회원 조회
        RestaurantRegisterRequest registerRequest = RestaurantRegisterRequest.builder()
                .restaurantId(restaurantId)
                .build();
        //return restaurantService.findUserById(registerRequest);
        return null;
    }

    //TODO : 정책상 제공하지 않으므로 추후 삭제
    @PutMapping("/{restaurantId}")
    public void modifyUserById(@PathVariable String restaurantId
            , @RequestBody RestaurantRegisterVO restaurantInput) {
        // 가게 회원 정보 수정
        RestaurantRegisterRequest registerRequest = RestaurantRegisterRequest.builder()
                .restaurantId(restaurantId)
                .name(restaurantInput.getName())
                .build();
        //restaurantService.modifyUserById(restaurant);
    }




    @PostMapping("/{restaurantId}/menus")
    public void createMenuById(@PathVariable String restaurantId
            , @RequestBody MenuInput menuInput) {
        // 메뉴를 생성한다.
        MenuInput menu = MenuInput.builder()
                .menuId(String.valueOf(UUID.randomUUID()))
                .restaurantId(restaurantId)
                .name(menuInput.getName())
                .build();
        menuService.createMenuById(menu);
    }

    @GetMapping("/{restaurantId}/menus")
    public List<MenuDto> findMenuById(@PathVariable String restaurantId) {
        // 해당 레스토랑의 메뉴들을 조회한다.
        MenuInput menu = MenuInput.builder()
                .restaurantId(restaurantId)
                .build();
        return menuService.findMenuById(menu);
    }

    @PutMapping("/{restaurantId}/menus/{menuId}")
    public void modifyMenuById(@PathVariable String restaurantId
            , @PathVariable String menuId
            , @RequestBody MenuInput menuInput) {
        // 메뉴 정보를 수정한다.
        MenuInput menu = MenuInput.builder()
                .restaurantId(restaurantId)
                .menuId(menuId)
                .name(menuInput.getName())
                .build();
        menuService.modifyMenuById(menu);
    }


    @GetMapping("/{restaurantId}/menus/{menuId}")
    public List<SubOptionInput> findSubOptions(@PathVariable String menuId) {
        // 해당 메뉴의 하위옵션들을 조회한다.
        log.trace("findSubOptionById 컨트롤러 호출");
        OptionInput optionInput = OptionInput.builder()
                .menuId(menuId)
                .build();

        List<OptionInput> optionOutputs = optionService.findOptionById(optionInput);
        List<SubOptionInput> subOptionOutputs = new ArrayList<>();

        for (OptionInput option : optionOutputs) {
            SubOptionInput subOptionInput = SubOptionInput.builder()
                    .menuId(option.getMenuId())
                    .optionId(option.getOptionId())
                    .build();

            subOptionOutputs.addAll(subOptionService.findSubOptionById(subOptionInput));
        }

        if (ObjectUtils.isEmpty(subOptionOutputs)) {
            log.warn("subOptions 테이블 조회결과 없음");
            return Collections.emptyList();
        }

        log.info(String.format("subOptions 테이블 조회성공. 조회결과 count=[{%s}]", subOptionOutputs.size()));

        return subOptionOutputs;
    }

}