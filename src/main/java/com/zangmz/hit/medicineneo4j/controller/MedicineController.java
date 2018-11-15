package com.zangmz.hit.medicineneo4j.controller;

import com.zangmz.hit.medicineneo4j.domain.Concept;
import com.zangmz.hit.medicineneo4j.domain.PrimaryClass;
import com.zangmz.hit.medicineneo4j.services.MedicineService;
import com.zangmz.hit.medicineneo4j.services.UMLSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class MedicineController {

    @Resource
    private MedicineService medicineService;

    @Resource
    private UMLSService umlsService;

    @RequestMapping("/primaryclass")
    public PrimaryClass findByName(@RequestParam String name){
        return medicineService.findByName(name);
    }

    @RequestMapping("/getConcept")
    public Concept getConceptByWord(@RequestParam String word){
        return medicineService.getConceptByWord(word);
    }

//    @RequestMapping("/getpath")
//    public double getShortestPath(@RequestParam String word1,@RequestParam String word2){
//        return medicineService.getShortestPath(word1, word2);
//    }

    @RequestMapping("/getpath")
    public Double getShortestPath(@RequestParam String word1, @RequestParam String word2){
        return medicineService.getSimilarity(word1, word2);
    }

    @RequestMapping("/getcso")
    public Double getCso(@RequestParam String lib, @RequestParam String wordset){
        return medicineService.getCso(wordset, lib);

    }

    @RequestMapping("/testumls")
    public String testUMLS(){
        return umlsService.getTgt();
    }


}
