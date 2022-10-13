package com.deliveryfood.dao;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.model.SubOptionInput;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubOptionDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public SubOptionInput findSubOptionById(SubOptionDto subOptionDto) {
        return sqlSessionTemplate.selectOne("com.deliveryfood.mapper.SubOptionMapper.findSubOptionById", subOptionDto);
    }
}
