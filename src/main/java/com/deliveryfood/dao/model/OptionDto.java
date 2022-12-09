package com.deliveryfood.dao.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OptionDto {
    private String optionId;
    private String menuId;
    private String name;
    private String state;
}
