package com.deliveryfood.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MenuDto {
    private String menuId;
    private String restaurantId;
    private String name;
    private String state;
    //TODO : 나머지 필드도 추가 예정
}