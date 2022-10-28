package com.deliveryfood.service;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.model.OptionInput;

import java.util.List;

public interface IOptionService {

    void createOption(OptionInput optionInput);
    void deleteOptionById(OptionInput optionInput);
    List<OptionInput> findOptionById(OptionInput optionInput);
}
