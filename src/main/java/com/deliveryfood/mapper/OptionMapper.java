package com.deliveryfood.mapper;

import com.deliveryfood.dao.model.OptionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper {

    void createOption(OptionDto optionDto);
    void deleteOptionById(OptionDto optionDto);
    List<OptionDto> findOptionById(OptionDto optionDto);
}
