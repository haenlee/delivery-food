package com.deliveryfood.service.model;

import com.deliveryfood.controller.model.request.SubOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class SubOptionVO {
    private String menuId;
    private String optionId;
    private String subOptionId;
    private String name;
    private String price;

    public static SubOptionVO convert(SubOptionRequest subOptionRequest) {
        return SubOptionVO.builder()
                .menuId(subOptionRequest.getMenuId())
                .optionId(subOptionRequest.getOptionId())
                .subOptionId(subOptionRequest.getSubOptionId())
                .name(subOptionRequest.getName())
                .price(subOptionRequest.getPrice())
                .build();
    }
}

