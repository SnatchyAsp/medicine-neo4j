package com.zangmz.hit.medicineneo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationInput {

    private HeadInput head;
    private String relation;
    private HeadInput tail;
    private String sentence;

    @Override
    public String toString() {
        return "RelationInput{" +
                "head=" + head +
                ", relation='" + relation + '\'' +
                ", tail=" + tail +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}
