package com.zangmz.hit.medicineneo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeadInput {
    String word;
    String type;
    String id;
    String semType;

    @Override
    public String toString() {
        return "HeadInput{" +
                "word='" + word + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
