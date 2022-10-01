package com.deliveryfood.mapper;

import com.deliveryfood.dto.UserDto;
import com.deliveryfood.model.UserInput;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto findById(String email);

    void register(UserDto userDto);

    void updateUser(UserDto userDto);
}
