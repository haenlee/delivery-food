package com.deliveryfood.service.impl;

import com.deliveryfood.dao.SubOptionDao;
import com.deliveryfood.dao.model.SubOptionDto;
import com.deliveryfood.service.ISubOptionService;
import com.deliveryfood.service.model.SubOptionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubOptionService implements ISubOptionService {

    private final SubOptionDao subOptionDao;

    @Override
    public void createSubOption(SubOptionVO subOptionVO) {
        log.trace("createSubOption 서비스 호출");
        SubOptionDto subOptionDto = SubOptionDto.builder()
                .optionId(subOptionVO.getOptionId())
                .menuId(subOptionVO.getMenuId())
                .subOptionId(subOptionVO.getSubOptionId())
                .build();
        subOptionDao.createSubOption(subOptionDto);
    }

    @Override
    public void deleteSubOptionById(SubOptionVO subOptionVO) {
        log.trace("deleteSubOptionById 서비스 호출");
        SubOptionDto subOptionDto = SubOptionDto.builder()
                .subOptionId(subOptionVO.getSubOptionId())
                .build();
        subOptionDao.deleteSubOptionById(subOptionDto);
    }

    @Override
    public List<SubOptionDto> findSubOptionById(SubOptionVO subOptionVO) {
        log.debug("findSubOptionById 서비스 호출");
        SubOptionDto subOptionDto = SubOptionDto.builder()
                .optionId(subOptionVO.getOptionId())
                .menuId(subOptionVO.getMenuId())
                .build();
        return subOptionDao.findSubOptionById(subOptionDto);
    }

}
