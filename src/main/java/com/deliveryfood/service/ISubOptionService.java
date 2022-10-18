package com.deliveryfood.service;

import com.deliveryfood.model.SubOptionInput;

import java.util.List;

public interface ISubOptionService {

    List<SubOptionInput> findSubOptionById(SubOptionInput subOptionInput);
}
