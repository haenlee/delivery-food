package com.deliveryfood.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OptionDto {
    private String optionId;
    private String menuId;
    private String name;
    private String state;
    //TODO : 나머지 필드도 추가 예정
}
