package com.deliveryfood.service.model;

import com.deliveryfood.controller.model.request.RestaurantMenuOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RestaurantMenuOptionVO {
    private String menuId;

    public static OptionVO convert(RestaurantMenuOptionRequest restaurantMenuOptionRequest) {
        return OptionVO.builder()
                .menuId(restaurantMenuOptionRequest.getMenuId())
                .build();
    }
}
