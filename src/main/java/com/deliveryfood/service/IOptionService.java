package com.deliveryfood.service;

import com.deliveryfood.model.MenuInput;
import com.deliveryfood.model.OptionInput;

import java.util.List;

public interface IOptionService {

    void createOptionById(OptionInput optionInput);
    List<OptionInput> findOptionById(OptionInput optionInput);
}
