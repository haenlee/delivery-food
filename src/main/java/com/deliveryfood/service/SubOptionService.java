package com.deliveryfood.service;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.mapper.SubOptionMapper;
import com.deliveryfood.model.SubOptionInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubOptionService implements ISubOptionService{

    private final SubOptionMapper subOptionMapper;

    @Override
    public List<SubOptionInput> findSubOptionById(SubOptionInput subOptionInput) {
        log.info("findSubOptionById 서비스 호출");
        SubOptionDto subOptionDto = SubOptionDto.builder()
                .optionId(subOptionInput.getOptionId())
                .menuId(subOptionInput.getMenuId())
                .build();
        return subOptionMapper.findSubOptionById(subOptionDto);
    }

}
