package com.deliveryfood.service.model;

import com.deliveryfood.controller.model.request.MenuRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MenuVO {
    private String menuId;
    private String restaurantId;
    private String name;
    private String state;

    public static MenuVO convert(String menuId, MenuRequest menuRequest) {
        return MenuVO.builder()
                .menuId(menuId)
                .restaurantId(menuRequest.getRestaurantId())
                .name(menuRequest.getName())
                .state(menuRequest.getState())
                .build();
    }
}
