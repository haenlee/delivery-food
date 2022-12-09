package com.deliveryfood.dao.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderMenuDto {
    private String orderId;
    private String userId;
    private String state;
}