package com.deliveryfood.service.impl;

import com.deliveryfood.dao.MenuDao;
import com.deliveryfood.dao.model.MenuDto;
import com.deliveryfood.service.IMenuService;
import com.deliveryfood.service.model.MenuRegisterVO;
import com.deliveryfood.service.model.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {

    private final MenuDao menuDao;

    @Override
    public void createMenuById(MenuRegisterVO menuRegisterVO) {
        MenuDto menuDto = MenuDto.builder()
                .menuId(menuRegisterVO.getMenuId())
                .restaurantId(menuRegisterVO.getRestaurantId())
                .name(menuRegisterVO.getName())
                .build();
        menuDao.createMenuById(menuDto);
    }

    @Override
    public List<MenuDto> findMenuById(MenuVO menuVO) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuVO.getRestaurantId())
                .build();
        return menuDao.findMenuById(menuDto);
    }

    @Override
    public void modifyMenuById(MenuVO menuVO) {
        MenuDto menuDto = MenuDto.builder()
                .restaurantId(menuVO.getRestaurantId())
                .menuId(menuVO.getMenuId())
                .name(menuVO.getName())
                .build();
        menuDao.modifyMenuById(menuDto);
    }

    @Override
    public void deleteByMenuId(String menuId) {
        menuDao.deleteByMenuId(menuId);
    }
}