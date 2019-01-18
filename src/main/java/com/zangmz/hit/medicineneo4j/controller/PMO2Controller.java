package com.zangmz.hit.medicineneo4j.controller;


import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.services.PMO2Service;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/pmo2/")
public class PMO2Controller {

    @Resource
    private PMO2Service pmo2Service;



    @RequestMapping("getKlassByName")
    public Klass getKlassByName(@RequestParam String word){
        return pmo2Service.getKlassByName(word);
    }

    @RequestMapping("test")
    public Klass test(){
//        return pmo2Service.testdb();
        return pmo2Service.testdb();
    }

    @RequestMapping("getTextRelation")
    public void textRelation(){
        pmo2Service.writeAllRelation("E:\\zangmz\\Intestinal_liver_tumor_mutation\\full_relations\\result\\result11969_copy");
    }

    @RequestMapping("getRelationPred")
    public void getRelationPred(){
        pmo2Service.writeRelationPred(new File("E:\\zangmz\\relation_extract\\pred_text.txt"));
    }


}
