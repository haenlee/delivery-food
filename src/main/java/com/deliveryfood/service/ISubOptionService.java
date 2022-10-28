package com.deliveryfood.service;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.model.SubOptionInput;

import java.util.List;

public interface ISubOptionService {

    void createSubOption(SubOptionInput subOptionInput);
    void deleteSubOptionById(SubOptionInput subOptionInput);
    List<SubOptionInput> findSubOptionById(SubOptionInput subOptionInput);
}
