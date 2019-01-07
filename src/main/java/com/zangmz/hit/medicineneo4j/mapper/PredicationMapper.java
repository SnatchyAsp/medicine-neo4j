package com.zangmz.hit.medicineneo4j.mapper;


import com.zangmz.hit.medicineneo4j.domain.Predication;

public interface PredicationMapper {
    int deleteByPrimaryKey(Integer predicationId);

    int insert(Predication record);

    int insertSelective(Predication record);

    Predication selectByPrimaryKey(Integer predicationId);

    int updateByPrimaryKeySelective(Predication record);

    int updateByPrimaryKey(Predication record);
}