package com.deliveryfood.dao.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MenuDto {
    private String menuId;
    private String restaurantId;
    private String name;
    private String state;
}