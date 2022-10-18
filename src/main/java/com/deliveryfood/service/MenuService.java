package com.deliveryfood.service;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.model.MenuInput;
import com.deliveryfood.model.RestaurantInput;

import java.util.List;

public interface MenuService {

    void createMenuById(MenuInput menuInput);
//    MenuDto findMenus(MenuInput menuInput);
    List<MenuDto> findMenuById(MenuInput menuInput);
    void modifyMenuById(MenuInput menuInput);
}