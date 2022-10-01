package com.deliveryfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public enum Status {
        NONE,
        REGISTER_AUTH,
        REGISTER,
        WITHDRAW,
    }

    private String userId;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String address;
    private Status status;
    private boolean isAuth;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;
}
