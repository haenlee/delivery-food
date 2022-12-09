package com.deliveryfood.mapper;

import com.deliveryfood.dao.model.SubOptionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubOptionMapper {

    void createSubOption(SubOptionDto subOptionDto);
    void deleteSubOptionById(SubOptionDto subOptionDto);
    List<SubOptionDto> findSubOptionById(SubOptionDto subOptionDto);
}
