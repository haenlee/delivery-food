package com.deliveryfood.service;

import com.deliveryfood.dao.model.SubOptionDto;
import com.deliveryfood.service.model.SubOptionVO;

import java.util.List;

public interface ISubOptionService {

    void createSubOption(SubOptionVO subOptionVO);
    void deleteSubOptionById(SubOptionVO subOptionVO);
    List<SubOptionDto> findSubOptionById(SubOptionVO subOptionVO);
}
