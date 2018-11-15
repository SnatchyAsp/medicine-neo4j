package com.zangmz.hit.medicineneo4j.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Setter
@Getter
@ToString(exclude = "id")
public class PrimaryClass {

    @Id
    @GeneratedValue
    private Long id;
    private String cid;
    private String name;


}
