package com.deliveryfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force=true)
@AllArgsConstructor
@Builder
@Value
public class OptionInput {
    private String optionId;
    private String menuId;
    private String name;
    private String state;
    //TODO : 나머지 필드도 추가 예정
}
