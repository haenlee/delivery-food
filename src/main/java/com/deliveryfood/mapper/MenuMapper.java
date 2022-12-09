package com.deliveryfood.mapper;


import com.deliveryfood.dao.model.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    void createMenuById(MenuDto menuDto);
    List<MenuDto> findMenuById(MenuDto menuDto);
    void modifyMenuById(MenuDto menuDto);
    void deleteByMenuId(String menuId);
}