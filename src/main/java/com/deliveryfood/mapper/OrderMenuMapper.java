package com.deliveryfood.mapper;


import com.deliveryfood.dto.OrderMenuDto;
import com.deliveryfood.model.OrderMenuInput;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMenuMapper {

    OrderMenuInput findOrderMenuById(OrderMenuDto orderMenuDto);
    void createOrderMenuById(OrderMenuDto orderMenuDto);
    void deleteOrderMenuById(OrderMenuDto orderMenuDto);
}