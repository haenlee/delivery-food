package com.deliveryfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Value
public class UserInput {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String nickname;
    private String imagePath;
}
