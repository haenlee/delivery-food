package com.deliveryfood.service;

import com.deliveryfood.model.OptionInput;

import java.util.List;

public interface IOptionService {

    void createOption(OptionInput optionInput);
    void deleteOptions();
    List<OptionInput> findOptionById(OptionInput optionInput);
}
