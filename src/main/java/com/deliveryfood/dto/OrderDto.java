package com.deliveryfood.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderDto {
    private String orderId;
    private String userId;
    private String state;
}