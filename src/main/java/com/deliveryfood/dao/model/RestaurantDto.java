package com.deliveryfood.dao.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class RestaurantDto {
    private String restaurantId;
    private String userId;
    private String name;
    private String state;
}