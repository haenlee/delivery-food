package com.deliveryfood.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SubOptionDto {
    private String menuId;
    private String optionId;
    private String subOptionId;
    private String name;
    private String price;
    //TODO : 나머지 필드도 추가 예정
}
