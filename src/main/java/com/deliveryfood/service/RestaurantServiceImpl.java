package com.deliveryfood.service;

import com.deliveryfood.dto.RestaurantDto;
import com.deliveryfood.mapper.RestaurantMapper;
import com.deliveryfood.model.RestaurantInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantMapper restaurantMapper;

    @Override
    public void signin(RestaurantInput restaurantInput) {
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurantInput.getRestaurantId())
                .userId(restaurantInput.getUserId())
                .name(restaurantInput.getName())
                .build();
        restaurantMapper.signin(restaurantDto);
    }

    @Override
    public void signout(RestaurantInput restaurantInput) {
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurantInput.getRestaurantId())
                .build();
        restaurantMapper.signout(restaurantDto);
    }

    @Override
    public List<RestaurantDto> findUsers() {
        return restaurantMapper.findUsers();
    }

    @Override
    public RestaurantDto findUserById(RestaurantInput restaurantInput) {
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurantInput.getRestaurantId())
                .userId(restaurantInput.getUserId())
                .build();
        return restaurantMapper.findUserById(restaurantDto);
    }

    @Override
    public void modifyUserById(RestaurantInput restaurantInput) {
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .restaurantId(restaurantInput.getRestaurantId())
                .name(restaurantInput.getName())
                .build();
        restaurantMapper.modifyUserById(restaurantDto);
    }


}