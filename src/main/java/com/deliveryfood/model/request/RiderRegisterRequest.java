package com.deliveryfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RiderRegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private int commission;
}
