package com.deliveryfood.service;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.mapper.OptionMapper;
import com.deliveryfood.model.OptionInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OptionService implements IOptionService{

    private final OptionMapper optionMapper;

    @Override
    public void createOptionById(OptionInput optionInput) {
        OptionDto optionDto = OptionDto.builder()
                .optionId(optionInput.getOptionId())
                .menuId(optionInput.getMenuId())
                .name(optionInput.getName())
                .build();
        optionMapper.createOptionById(optionDto);
    }

    @Override
    public List<OptionInput> findOptionById(OptionInput optionInput) {
        log.debug("findOptionById 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .menuId(optionInput.getMenuId())
                .build();
        return optionMapper.findOptionById(optionDto);
    }
}
