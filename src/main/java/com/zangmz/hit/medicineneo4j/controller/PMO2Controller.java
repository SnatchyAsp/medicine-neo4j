package com.zangmz.hit.medicineneo4j.controller;


import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.bo.res.RelationListRes;
import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.services.PMO2Service;

import com.zangmz.hit.medicineneo4j.utils.Newrelation;
import com.zangmz.hit.medicineneo4j.utils.Webinfo;
import org.json.JSONArray;

import org.neo4j.ogm.request.Request;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/pmo2/")
public class PMO2Controller {

    @Resource
    private PMO2Service pmo2Service;
    private ArrayList<Relation> test11=new ArrayList<>();
    private int all_returncode=-1;
    private String all_returnmessage;



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




    @RequestMapping(value = "getRelation", method = RequestMethod.POST)
    public Map<String, Newrelation.info> getRelation(@ModelAttribute Webinfo webinfo){

        Integer return_code=-1;
        String return_message="";
        Map<String, Newrelation.info> rels = new HashMap<>();
        if(webinfo.getHead_entity().equals("")){
             webinfo.setHead_entity("###");
        }
        if(webinfo.getTail_entity().equals("")){
            webinfo.setTail_entity("###");
        }
        if(webinfo.isPmoz_rel()){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(webinfo.getHead_entity(), webinfo.getTail_entity(), "pmoz_rel");
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(com.alibaba.fastjson.JSONObject.toJSONString(result));

            if(jsonObject.getInteger("returnCode")==0){
                return_code = jsonObject.getInteger("returnCode");
                return_message = jsonObject.getString("returnMessage");
                Newrelation newrelation = new Newrelation();
                Map<String, Newrelation.info> rel = newrelation.infoin(jsonObject.getJSONArray("data"),"pmoz_rel");
                rels.putAll(rel);

            }
            else{
                if(return_code!=0){
                    return_code = jsonObject.getInteger("returnCode");
                    return_message = jsonObject.getString("returnMessage");
                }
            }

        }
        if(webinfo.isSubclass_of()){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(webinfo.getHead_entity(), webinfo.getTail_entity(), "subclass_of");
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result));
            if(jsonObject.getInteger("returnCode")==0){
                return_code = jsonObject.getInteger("returnCode");
                return_message = jsonObject.getString("returnMessage");
                Newrelation newrelation = new Newrelation();
                Map<String, Newrelation.info> rel = newrelation.infoin(jsonObject.getJSONArray("data"),"subclass_of");
                rels.putAll(rel);



            }
            else{
                if(return_code!=0){
                    return_code = jsonObject.getInteger("returnCode");
                    return_message = jsonObject.getString("returnMessage");

                }
            }
        }
        if(webinfo.isUmls_pcnn()){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(webinfo.getHead_entity(), webinfo.getTail_entity(), "umls_pcnn");
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result));
            if(jsonObject.getInteger("returnCode")==0){
                return_code = jsonObject.getInteger("returnCode");
                return_message = jsonObject.getString("returnMessage");
                Newrelation newrelation = new Newrelation();
                Map<String, Newrelation.info> rel = newrelation.infoin(jsonObject.getJSONArray("data"),"umls_pcnn");
                rels.putAll(rel);



            }
            else{
                if(return_code!=0){
                    return_code = jsonObject.getInteger("returnCode");
                    return_message = jsonObject.getString("returnMessage");
                }
            }
        }
        if(webinfo.isUmls_rel()){
            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(webinfo.getHead_entity(), webinfo.getTail_entity(), "umls_rel");
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result));
            if(jsonObject.getInteger("returnCode")==0){
                return_code = jsonObject.getInteger("returnCode");
                return_message = jsonObject.getString("returnMessage");
                Newrelation newrelation = new Newrelation();
                Map<String, Newrelation.info> rel = newrelation.infoin(jsonObject.getJSONArray("data"),"umls_rel");
                rels.putAll(rel);



            }
            else{
                if(return_code!=0){
                    return_code = jsonObject.getInteger("returnCode");
                    return_message = jsonObject.getString("returnMessage");
                }
            }
        }
        Newrelation.info temp = new Newrelation.info();
        temp.setSubject_name(return_code.toString());
        temp.setObject_name(return_message);
        rels.put("return",temp);
        return rels;
    }


    @RequestMapping(value = "/input")
    public ModelAndView user() {
        test11=new ArrayList<>();
        all_returncode=-1;
        all_returnmessage="";
        return new ModelAndView("input", "command", new Relation());
    }


    @RequestMapping("/getNodes")
    public String getnodes(HttpServletRequest req) {
        String id = req.getParameter("id");
        if(id == null){
            id = "0";
        }
        System.out.println(id);
        return pmo2Service.getNodes(id);
        //return "get request";
    }

}
