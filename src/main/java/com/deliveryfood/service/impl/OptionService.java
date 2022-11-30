package com.deliveryfood.service.impl;

import com.deliveryfood.dao.OptionDao;
import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.service.IOptionService;
import com.deliveryfood.service.model.OptionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OptionService implements IOptionService {

    private final OptionDao optionDao;

    @Override
    public void createOption(OptionVO optionVO) {
        log.trace("createOption 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .optionId(optionVO.getOptionId())
                .menuId(optionVO.getMenuId())
                .name(optionVO.getName())
                .build();
        optionDao.createOption(optionDto);
    }

    @Override
    public void deleteOptionById(OptionVO optionVO) {
        log.trace("deleteOptionById 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .optionId(optionVO.getOptionId())
                .build();
        optionDao.deleteOptionById(optionDto);
    }

    @Override
    public List<OptionDto> findOptionById(OptionVO optionVO) {
        log.trace("findOptionById 서비스 호출");
        OptionDto optionDto = OptionDto.builder()
                .menuId(optionVO.getMenuId())
                .build();
        return optionDao.findOptionById(optionDto);
    }
}
