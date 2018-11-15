package com.zangmz.hit.medicineneo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationSample {

    private String head_entity_id;
    private String tail_entity_id;
    private String head_entity;
    private String tail_entity;
    private String relation;
    private String sentence;

    @Override
    public String toString() {
        return "RelationSample{" +
                "head_entity_id='" + head_entity_id + '\'' +
                ", tail_entity_id='" + tail_entity_id + '\'' +
                ", head_entity='" + head_entity + '\'' +
                ", tail_entity='" + tail_entity + '\'' +
                ", relation='" + relation + '\'' +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}
