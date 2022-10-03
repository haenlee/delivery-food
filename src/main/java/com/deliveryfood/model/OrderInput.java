package com.deliveryfood.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force=true)
@AllArgsConstructor
@Builder
@Value
public class OrderInput {
    private String orderId;
    private String userId;
    private String state;
    //TODO : 나머지 필드도 추가 예정
}
