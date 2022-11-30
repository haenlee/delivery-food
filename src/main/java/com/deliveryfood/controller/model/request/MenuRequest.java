package com.deliveryfood.controller.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force=true)
@AllArgsConstructor
@Builder
@Value
public class MenuRequest {
    private String menuId;
    private String restaurantId;
    private String name;
    private String state;
}
