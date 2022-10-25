package com.deliveryfood.service;

import com.deliveryfood.model.SubOptionInput;

import java.util.List;

public interface ISubOptionService {

    void createSubOption(SubOptionInput subOptionInput);
    void deleteSubOptions();
    List<SubOptionInput> findSubOptionById(SubOptionInput subOptionInput);
}
