package com.deliveryfood.dao;

import com.deliveryfood.dto.MenuDto;
import com.deliveryfood.dto.OptionDto;
import com.deliveryfood.model.OptionInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OptionDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void createOptionById(OptionDto optionDto) {
        sqlSessionTemplate.insert("com.deliveryfood.mapper.OptionMapper.createOptionById", optionDto);
    }

    public List<OptionInput> findOptionById(OptionDto optionDto) {
        log.info("findOptionById DAO 호출");
        return sqlSessionTemplate.selectList("com.deliveryfood.mapper.OptionMapper.findOptionById", optionDto);
    }
}
