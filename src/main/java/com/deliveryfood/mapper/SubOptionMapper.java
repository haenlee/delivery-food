package com.deliveryfood.mapper;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.model.SubOptionInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubOptionMapper {

    void createSubOption(SubOptionDto subOptionDto);
    void deleteSubOptions();
    List<SubOptionInput> findSubOptionById(SubOptionDto subOptionDto);
}
