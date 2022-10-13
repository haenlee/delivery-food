package com.deliveryfood.service;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.mapper.OptionMapper;
import com.deliveryfood.model.OptionInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMenuService implements IOptionService{

    private final OptionMapper optionMapper;

    @Override
    public OptionInput findOptionById(OptionInput optionInput) {
        OptionDto optionDto = OptionDto.builder()
                .optionId(optionInput.getOptionId())
                .menuId(optionInput.getMenuId())
                .build();
        return optionMapper.findOptionById(optionDto);
    }
}
