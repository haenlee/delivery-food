package com.deliveryfood.mapper;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.model.SubOptionInput;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubOptionMapper {

    SubOptionInput findSubOptionById(SubOptionDto subOptionDto);
}
