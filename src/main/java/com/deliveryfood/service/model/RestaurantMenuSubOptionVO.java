package com.deliveryfood.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RestaurantMenuSubOptionVO {
    private String menuId;
    private String optionId;

    public static SubOptionVO convert(String menuId, String optionId) {
        return SubOptionVO.builder()
                .menuId(menuId)
                .optionId(optionId)
                .build();
    }
}

