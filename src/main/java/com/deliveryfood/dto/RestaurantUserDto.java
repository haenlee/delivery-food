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
public class RestaurantUserDto {

    @NonNull
    private String userId;
    @NonNull
    private LocalDateTime regDt;
    @NonNull
    private LocalDateTime udtDt;
}
