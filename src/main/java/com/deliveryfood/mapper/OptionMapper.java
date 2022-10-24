package com.deliveryfood.mapper;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.model.OptionInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper {

    void createOptionById(OptionDto optionDto);
    List<OptionInput> findOptionById(OptionDto optionDto);
}
