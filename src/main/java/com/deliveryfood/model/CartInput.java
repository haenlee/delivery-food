package com.deliveryfood.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CartInput {
    int menuId;
    int optionId;
    int subOptionid;
}
