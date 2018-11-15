package com.zangmz.hit.medicineneo4j.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;


@Getter
@Setter
@ToString(exclude = "id")
public class Concept {

    @Id
    @GeneratedValue
    private Long id;
    private String cid;

}
