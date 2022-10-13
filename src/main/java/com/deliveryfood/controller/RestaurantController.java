package com.deliveryfood.controller;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.model.*;
import com.deliveryfood.service.IOptionService;
import com.deliveryfood.service.ISubOptionService;
import com.deliveryfood.service.MenuService;
import com.deliveryfood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final IOptionService optionService;
    private final ISubOptionService subOptionService;

    @PostMapping("/certification")
    public void certification(@RequestParam int code) {
        // 입력한 코드로 본인 인증
    }

    @PostMapping("/register")
    public void register(UserInput userInput) {
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
        RestaurantInput restaurant = RestaurantInput.builder()
                .restaurantId(restaurantId)
                .build();
        return restaurantService.findUserById(restaurant);
    }

    //TODO : 정책상 제공하지 않으므로 추후 삭제
    @PutMapping("/{restaurantId}")
    public void modifyUserById(@PathVariable String restaurantId
            , @RequestBody RestaurantInput restaurantInput) {
        // 가게 회원 정보 수정
        RestaurantInput restaurant = RestaurantInput.builder()
                .restaurantId(restaurantId)
                .name(restaurantInput.getName())
                .build();
        restaurantService.modifyUserById(restaurant);
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

    @GetMapping("/{restaurantId}/menus/{menuId}")
    public MenuDto findMenus(@PathVariable String restaurantId
            , @PathVariable String menuId) {
        // 메뉴를 조회한다.
        MenuInput menu = MenuInput.builder()
                .restaurantId(restaurantId)
                .menuId(menuId)
                .build();
        return menuService.findMenus(menu);
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


    @GetMapping("/menus/options")
    public OptionInput findOptionById(@RequestBody OptionInput optionInput) {
        // 해당 레스토랑의 메뉴옵션들을 조회한다.
        log.info("findOptionById 컨트롤러 호출");
        OptionInput option = OptionInput.builder()
                .optionId(optionInput.getOptionId())
                .menuId(optionInput.getMenuId())
                .build();
        return optionService.findOptionById(option);
    }


    @GetMapping("/menus/subOptions")
    public SubOptionInput findSubOptionById(@RequestBody SubOptionInput subOptionInput) {
        // 해당 레스토랑의 메뉴하위옵션들을 조회한다.
        log.info("findSubOptionById 컨트롤러 호출");
        SubOptionInput subOption = SubOptionInput.builder()
                .optionId(subOptionInput.getOptionId())
                .menuId(subOptionInput.getMenuId())
                .build();
        return subOptionService.findSubOptionById(subOption);
    }
}