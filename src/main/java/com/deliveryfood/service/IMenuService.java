package com.deliveryfood.service;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.model.MenuInput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMenuService {

    void createMenuById(MenuInput menuInput);
    MenuDto findMenus(MenuInput menuInput);
    List<MenuDto> findMenuById(MenuInput menuInput);
    void modifyMenuById(MenuInput menuInput);
}