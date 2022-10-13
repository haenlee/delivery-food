package com.deliveryfood.dao;

import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.model.OptionInput;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OptionDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public OptionInput findOptionById(OptionDto optionDto) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.OptionMapper.findOptionById", optionDto);
    }
}
