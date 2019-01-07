package com.zangmz.hit.medicineneo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Relation {

    private String relation;
    private int num;

    @Override
    public String toString() {
        return "Relation{" +
                "relation='" + relation + '\'' +
                ", num=" + num +
                '}';
    }

    public Relation(String relation, int num) {
        this.relation = relation;
        this.num = num;
    }
}
