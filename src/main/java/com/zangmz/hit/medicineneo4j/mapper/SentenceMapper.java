package com.zangmz.hit.medicineneo4j.mapper;

import com.zangmz.hit.medicineneo4j.domain.Sentence;

public interface SentenceMapper {
    int deleteByPrimaryKey(Integer sentenceId);

    int insert(Sentence record);

    int insertSelective(Sentence record);

    Sentence selectByPrimaryKey(Integer sentenceId);

    int updateByPrimaryKeySelective(Sentence record);

    int updateByPrimaryKey(Sentence record);
}