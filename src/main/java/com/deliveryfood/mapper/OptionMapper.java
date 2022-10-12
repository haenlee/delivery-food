package com.deliveryfood.mapper;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.model.OptionInput;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {

    OptionInput findOptionById(OptionDto optionDto);
}
