package com.zangmz.hit.medicineneo4j.pmo2domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;

@Getter
@Setter
@ToString
@NodeEntity(label="instance")
public class Instance {

    @Id
    @GeneratedValue
    private String id;
    private String MCID;
    private String name;
    private String subclass_of;
}
