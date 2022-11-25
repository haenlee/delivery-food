package com.deliveryfood.service.model;

import com.deliveryfood.controller.model.request.OptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class OptionVO {
    private String optionId;
    private String menuId;
    private String name;
    private String state;

    public static OptionVO convert(OptionRequest optionRequest) {
        return OptionVO.builder()
                .optionId(optionRequest.getOptionId())
                .menuId(optionRequest.getMenuId())
                .name(optionRequest.getName())
                .state(optionRequest.getState())
                .build();
    }
}
