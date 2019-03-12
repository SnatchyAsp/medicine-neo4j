package com.zangmz.hit.medicineneo4j.controller;


import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.bo.res.RelationListRes;
import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.services.PMO2Service;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.hibernate.validator.constraints.pl.REGON;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


//    @RequestMapping("getRelation")
//    public BaseRes<PMO2Relation> getRelation(@RequestParam String subject, @RequestParam String object, @RequestParam String relationType){
//        RelationListRes relationListRes = null;
//        BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(subject, object, relationType);
//
//        return result;
//    }

    @RequestMapping(value = "getRelation", method = RequestMethod.POST)
    public ModelAndView getRelation(@ModelAttribute("SpringWeb")Relation rel, ModelMap model){
        RelationListRes relationListRes = null;

        if(rel.getUmls_pcnn()!= null){

            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_pcnn());
            JSONObject test = new JSONObject(result);
            String data = test.toString();
            //JSONObject test = new JSONObject(result);
            rel.setinfo(test, "umls_pcnn");
            model.addAttribute("umls_pcnn_data",data);
        }
        if(rel.getUmls_rel()!=null){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_rel());
            JSONObject test = new JSONObject(result);
            String data = test.toString();
            //JSONObject test = new JSONObject(result);
            rel.setinfo(test, "umls_rel");
            model.addAttribute("umls_rel_data",data);
        }
        if(rel.getPmoz_rel()!=null){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getPmoz_rel());
            JSONObject test = new JSONObject(result);
            String data = test.toString();
            //JSONObject test = new JSONObject(result);
            rel.setinfo(test, "pmoz_rel");
            model.addAttribute("pmoz_rel_data",data);
        }
        if(rel.getSubclass_of()!=null){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getSubclass_of());
            JSONObject test = new JSONObject(result);
            String data = test.toString();
            rel.setinfo(test, "subclass_of");
            model.addAttribute("subclass_of_data",data);
        }
        //JSONObject test = new JSONObject(rel);
        String test = com.alibaba.fastjson.JSON.toJSONString(rel);
        Relation newrel = com.alibaba.fastjson.JSON.parseObject(test, Relation.class);
        model.addAttribute("rel",test);
        model.addAttribute("head_entity", rel.getHead_entity());
        model.addAttribute("tail_entity", rel.getTail_entity());
        model.addAttribute("umls_pcnn", rel.getUmls_pcnn());
        model.addAttribute("umls_rel", rel.getUmls_rel());
        model.addAttribute("pmoz_rel", rel.getPmoz_rel());
        model.addAttribute("subclass_of", rel.getSubclass_of());

        return new ModelAndView("getRelation", "command", new Relation());
    }


    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("input", "command", new Relation());
    }



}
