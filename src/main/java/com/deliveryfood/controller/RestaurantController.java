package com.deliveryfood.controller;

import com.deliveryfood.controller.model.request.MenuRequest;
import com.deliveryfood.controller.model.request.RestaurantMenuOptionRequest;
import com.deliveryfood.controller.model.request.RestaurantRegisterRequest;
import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.service.IMenuService;
import com.deliveryfood.service.IOptionService;
import com.deliveryfood.service.IRestaurantService;
import com.deliveryfood.service.ISubOptionService;
import com.deliveryfood.service.model.MenuRegisterVO;
import com.deliveryfood.service.model.MenuVO;
import com.deliveryfood.service.model.RestaurantMenuOptionVO;
import com.deliveryfood.service.model.RestaurantMenuSubOptionVO;
import com.deliveryfood.service.model.RestaurantRegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{restaurantId}")
    public RestaurantDto findUserById(@PathVariable String restaurantId
            , @RequestBody RestaurantRegisterRequest restaurantRegisterRequest) {
        // 레스토랑 회원 조회
        return restaurantService.findUserById(RestaurantRegisterVO.convert(restaurantRegisterRequest));
    }

    @PostMapping("/{restaurantId}/menus")
    public void createMenuById(@PathVariable String restaurantId
            , @RequestBody MenuRequest menuRequest) {
        // 메뉴를 생성한다.
        menuService.createMenuById(MenuRegisterVO.convert(String.valueOf(UUID.randomUUID()), menuRequest));
    }

    @GetMapping("/{restaurantId}/menus")
    public List<MenuDto> findMenuById(@PathVariable String restaurantId
            , @RequestBody MenuRequest menuRequest) {
        // 해당 레스토랑의 메뉴들을 조회한다.
        return menuService.findMenuById(MenuVO.convert(menuRequest.getMenuId(), menuRequest));
    }

    @PutMapping("/{restaurantId}/menus/{menuId}")
    public void modifyMenuById(@PathVariable String restaurantId
            , @PathVariable String menuId
            , @RequestBody MenuRequest menuRequest) {
        // 메뉴 정보를 수정한다.
        menuService.modifyMenuById(MenuVO.convert(menuId, menuRequest));
    }

    @GetMapping("/{restaurantId}/menus/{menuId}")
    public List<SubOptionDto> findSubOptions(@PathVariable String menuId
            , @RequestBody RestaurantMenuOptionRequest restaurantMenuOptionRequest) {
        // 해당 메뉴의 하위옵션들을 조회한다.
        List<OptionDto> optionDtos = optionService.findOptionById(RestaurantMenuOptionVO.convert(restaurantMenuOptionRequest));
        List<SubOptionDto> subOptionDtos = new ArrayList<>();
        for (OptionDto option : optionDtos) {
            subOptionDtos.addAll(subOptionService.findSubOptionById(RestaurantMenuSubOptionVO.convert(option.getMenuId(), option.getOptionId())));
        }

        if (ObjectUtils.isEmpty(subOptionDtos)) {
            log.warn("subOptions 테이블 조회결과 없음");
            return Collections.emptyList();
        }

        log.info(String.format("subOptions 테이블 조회성공. 조회결과 count=[{%s}]", subOptionDtos.size()));

        return subOptionDtos;
    }

}