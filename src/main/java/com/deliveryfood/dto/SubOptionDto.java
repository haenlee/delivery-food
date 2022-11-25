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
}
