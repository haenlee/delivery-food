package com.deliveryfood.mapper;


import com.deliveryfood.dao.model.OrderMenuDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMenuMapper {

    OrderMenuDto findOrderMenuById(OrderMenuDto orderMenuDto);
    void createOrderMenuById(OrderMenuDto orderMenuDto);
    void deleteOrderMenuById(OrderMenuDto orderMenuDto);
}