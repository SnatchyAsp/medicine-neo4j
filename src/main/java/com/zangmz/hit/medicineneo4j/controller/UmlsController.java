package com.zangmz.hit.medicineneo4j.controller;

import com.zangmz.hit.medicineneo4j.domain.Predication;
import com.zangmz.hit.medicineneo4j.services.UMLSService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/umls/")
public class UmlsController {

    @Resource
    UMLSService umlsService;

    @RequestMapping("getpredication")
    public Predication getPredication(@RequestParam int id) {
        return umlsService.getPredicationById(id);

    }

    @RequestMapping("getRelation")
    public void getRelation() {
        umlsService.getPredicationBatch(643000, 843000, 843000, 1043000);
    }

    @RequestMapping("getRelationByType")
    public void getRelationByType(){
        umlsService.getRelationByType("E:\\zangmz\\goldstandard\\adjudicated_rel.txt");
    }
}
