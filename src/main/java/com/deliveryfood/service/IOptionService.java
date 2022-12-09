package com.deliveryfood.service;

import com.deliveryfood.dao.model.OptionDto;
import com.deliveryfood.service.model.OptionVO;

import java.util.List;

public interface IOptionService {

    void createOption(OptionVO optionVO);
    void deleteOptionById(OptionVO optionVO);
    List<OptionDto> findOptionById(OptionVO optionVO);
}
