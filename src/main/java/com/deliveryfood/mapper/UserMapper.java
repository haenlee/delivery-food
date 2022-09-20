package com.deliveryfood.mapper;

import com.deliveryfood.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void register(UserDto user);
}
