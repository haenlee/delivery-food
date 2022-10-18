package com.deliveryfood.dao;

import com.deliveryfood.dto.SubOptionDto;
import com.deliveryfood.model.SubOptionInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SubOptionDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public List<SubOptionInput> findSubOptionById(SubOptionDto subOptionDto) {
        log.info("findOptionById DAO 호출");
        return sqlSessionTemplate.selectList("com.deliveryfood.mapper.SubOptionMapper.findSubOptionById", subOptionDto);
    }
}
