package com.deliveryfood.service;

import com.deliveryfood.model.OptionInput;

import java.util.List;

public interface IOptionService {

    List<OptionInput> findOptionById(OptionInput optionInput);
}
