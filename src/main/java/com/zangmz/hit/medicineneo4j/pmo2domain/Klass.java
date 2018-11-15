package com.zangmz.hit.medicineneo4j.pmo2domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Getter
@Setter
@ToString(exclude = "id")
@NodeEntity(label="class")
public class Klass {

    @Id
    @GeneratedValue
    private Long id;
    private String MCID;
    private String MTID;
    private String name;
    private String subclass_of;
    private String tree_number;

}
