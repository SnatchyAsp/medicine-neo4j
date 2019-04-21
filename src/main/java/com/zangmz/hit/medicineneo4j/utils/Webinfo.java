package com.zangmz.hit.medicineneo4j.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Webinfo {
    private boolean umls_pcnn,umls_rel,pmoz_rel,subclass_of;
    private String tail_entity,head_entity;

}
