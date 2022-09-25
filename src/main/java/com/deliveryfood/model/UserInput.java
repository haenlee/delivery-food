package com.deliveryfood.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserInput {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
}
