package com.zangmz.hit.medicineneo4j.pmo2domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

@Getter
@Setter
@ToString(exclude = "id")
public class UmlsRel {

    @Id
    @GeneratedValue
    private String id;
    private String relation;
    private String sentence;
    private String pmid;


}
