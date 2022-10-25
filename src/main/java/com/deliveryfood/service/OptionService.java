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
    public void createOption(OptionInput optionInput) {
        log.trace("createOption 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .optionId(optionInput.getOptionId())
                .menuId(optionInput.getMenuId())
                .name(optionInput.getName())
                .build();
        optionMapper.createOption(optionDto);
    }

    @Override
    public void deleteOptions() {
        log.trace("deleteOptions 서비스 호출");
        optionMapper.deleteOptions();
    }

    @Override
    public List<OptionInput> findOptionById(OptionInput optionInput) {
        log.trace("findOptionById 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .menuId(optionInput.getMenuId())
                .build();
        return optionMapper.findOptionById(optionDto);
    }
}
