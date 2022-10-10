package com.deliveryfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    public enum Status {
        NONE,
        REGISTER,
        WITHDRAW;
    }

    public enum Role {
        NONE,
        ROLE_USER,
        ROLE_RESTAURANT,
        ROLE_RIDER,
        ROLE_NOT_AUTH,
    }

    @NonNull
    private String userId;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String phone;
    @NonNull
    private Status status;
    @NonNull
    private Role role;
    @NonNull
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

}
