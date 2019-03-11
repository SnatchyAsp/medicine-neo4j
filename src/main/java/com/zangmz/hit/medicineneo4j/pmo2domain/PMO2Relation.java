package com.zangmz.hit.medicineneo4j.pmo2domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "id")
public class PMO2Relation {

    private Klass subject;
    private Klass object;
    private UmlsRel relation;

}
