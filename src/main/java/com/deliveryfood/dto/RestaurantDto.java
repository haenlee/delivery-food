package com.deliveryfood.dto;

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
    //TODO : 나머지 필드도 추가 예정
}