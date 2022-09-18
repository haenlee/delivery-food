package com.deliveryfood.model;

import lombok.Data;

@Data
public class CartInput {
    int menuId;
    int optionId;
    int subOptionid;
}
