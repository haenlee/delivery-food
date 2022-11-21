package com.deliveryfood.service;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.mapper.MenuMapper;
import com.deliveryfood.model.MenuInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {

    private final MenuMapper menuMapper;

    @Override
    public void createMenuById(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .menuId(menuInput.getMenuId())
                .restaurantId(menuInput.getRestaurantId())
                .name(menuInput.getName())
                .build();
        menuMapper.createMenuById(menuDto);
    }

    @Override
    public MenuDto findMenus(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .menuId(menuInput.getMenuId())
                .restaurantId(menuInput.getRestaurantId())
                .build();
        return menuMapper.findMenus(menuDto);
    }

    @Override
    public List<MenuDto> findMenuById(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuInput.getRestaurantId())
                .build();
        return menuMapper.findMenuById(menuDto);
    }

    @Override
    public void modifyMenuById(MenuInput menuInput) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuInput.getRestaurantId())
                .menuId(menuInput.getMenuId())
                .name(menuInput.getName())
                .build();
        menuMapper.modifyMenuById(menuDto);
    }
}