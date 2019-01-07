package com.zangmz.hit.medicineneo4j.mapper;

import com.zangmz.hit.medicineneo4j.domain.PredicationAux;

public interface PredicationAuxMapper {
    int deleteByPrimaryKey(Integer predicationAuxId);

    int insert(PredicationAux record);

    int insertSelective(PredicationAux record);

    PredicationAux selectByPrimaryKey(Integer predicationAuxId);

    PredicationAux selectByPredicationId(Integer predicationId);

    int updateByPrimaryKeySelective(PredicationAux record);

    int updateByPrimaryKey(PredicationAux record);
}