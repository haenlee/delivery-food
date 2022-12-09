package com.deliveryfood.dao.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public enum Grade {
        NONE,
        COMMON,
        VIP,
    }

    @NonNull
    private String userId;
    @NonNull
    private String address;
    private String nickname;
    private Grade grade;
    private String imagePath;
    @NonNull
    private LocalDateTime regDt;
    @NonNull
    private LocalDateTime udtDt;
}
