package com.deliveryfood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantInput extends MemberInput {
    private String restaurantId;
    private String userId;
    private String state;
    //TODO : 나머지 필드도 추가 예정
}
