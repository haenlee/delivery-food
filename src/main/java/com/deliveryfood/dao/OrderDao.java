package com.deliveryfood.dao;

import com.deliveryfood.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void createOrder(OrderDto orderDto) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.OrderMapper.createOrder", orderDto);
    }

    public OrderDto findOrder(OrderDto orderDto) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.OrderMapper.findOrder", orderDto.getOrderId());
    }

    public List<OrderDto> findOrderById(OrderDto orderDto) {
        return sqlSessionTemplate.selectList("com.deliveryfood.mapper.OrderMapper.findOrder", orderDto);
    }

    public void modifyOrderById(OrderDto orderDto) {
        sqlSessionTemplate.update("com.deliveryfood.mapper.OrderMapper.modifyOrderById", orderDto);
    }
}
