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
    public List<OptionInput> findOptionById(OptionInput optionInput) {
        log.info("findOptionById 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .menuId(optionInput.getMenuId())
                .build();
        return optionMapper.findOptionById(optionDto);
    }
}
