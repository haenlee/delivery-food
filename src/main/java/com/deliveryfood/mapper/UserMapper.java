package com.deliveryfood.mapper;

import com.deliveryfood.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto findById(String email);

    void register(UserDto userDto);

    void withdraw(UserDto userDto);
}
