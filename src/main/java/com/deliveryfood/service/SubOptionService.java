package com.deliveryfood.service;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.mapper.OptionMapper;
import com.deliveryfood.mapper.SubOptionMapper;
import com.deliveryfood.model.SubOptionInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubOptionService implements ISubOptionService{

    private final SubOptionMapper subOptionMapper;

    @Override
    public SubOptionInput findSubOptionById(SubOptionInput subOptionInput) {
        SubOptionDto subOptionDto = SubOptionDto.builder()
                .optionId(subOptionInput.getOptionId())
                .menuId(subOptionInput.getMenuId())
                .build();
        return subOptionMapper.findSubOptionById(subOptionDto);
    }

}
