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
public class MenuRegisterVO {
    private String menuId;
    private String restaurantId;
    private String name;
    private String state;

    public static MenuRegisterVO convert(String menuId, MenuRequest menuRequest) {
        return MenuRegisterVO.builder()
                .menuId(menuId)
                .restaurantId(menuRequest.getRestaurantId())
                .name(menuRequest.getName())
                .state(menuRequest.getState())
                .build();
    }
}
