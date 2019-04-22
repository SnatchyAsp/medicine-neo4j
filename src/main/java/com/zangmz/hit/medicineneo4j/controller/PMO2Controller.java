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

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
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




//    @RequestMapping(value = "getRelation", method = RequestMethod.POST)
//    public ModelAndView getRelation(@ModelAttribute("SpringWeb")Relation rel, ModelMap model){
//        RelationListRes relationListRes = null;
//        if(rel.getHead_entity()=="") rel.setHead_entity("###");
//        if(rel.getTail_entity()=="") rel.setTail_entity("###");
//        if(rel.getUmls_pcnn()!= null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_pcnn());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"umls_pcnn");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "umls_pcnn");
//        }
//        if(rel.getUmls_rel()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_rel());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"umls_rel");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "umls_rel");
//        }
//        if(rel.getPmoz_rel()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getPmoz_rel());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"pmoz_rel");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "pmoz_rel");
//
//        }
//        if(rel.getSubclass_of()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getSubclass_of());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"subclass_of");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "subclass_of");
//        }
//        if(rel.getReturn_code()==-1) rel.setReturn_message("Please select a type of relation");
//        /*if(rel.getReturn_code()==0){
//            test11.add(rel);
//        }*/
//        if(all_returncode!=0){
//            all_returncode=rel.getReturn_code();
//            all_returnmessage=rel.getReturn_message();
//            }
//
//        String test = com.alibaba.fastjson.JSON.toJSONString(rel);
//        model.addAttribute("rel",test);
//        String testlist = com.alibaba.fastjson.JSON.toJSONString(test11);
//        model.addAttribute("test11",testlist);
//        model.addAttribute("all_returncode",all_returncode);
//        model.addAttribute("all_returnmessage",all_returnmessage);
//
//
//        return new ModelAndView("getRelation", "command", new Relation());
//    }
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
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result));

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
//    public ModelAndView getRelation(@ModelAttribute("SpringWeb")Relation rel, ModelMap model){
//        RelationListRes relationListRes = null;
//        if(rel.getHead_entity()=="") rel.setHead_entity("###");
//        if(rel.getTail_entity()=="") rel.setTail_entity("###");
//        if(rel.getUmls_pcnn()!= null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_pcnn());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"umls_pcnn");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "umls_pcnn");
//        }
//        if(rel.getUmls_rel()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getUmls_rel());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"umls_rel");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "umls_rel");
//        }
//        if(rel.getPmoz_rel()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getPmoz_rel());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"pmoz_rel");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "pmoz_rel");
//
//        }
//        if(rel.getSubclass_of()!=null){
//            BaseRes<PMO2Relation> result = pmo2Service.getRelationBySubjectAndObject(rel.getHead_entity(), rel.getTail_entity(), rel.getSubclass_of());
//            JSONObject test = new JSONObject(result);
//            Integer temp_return_code=test.getInt("returnCode");
//            String temp_return_message=test.getString("returnMessage");
//            if(temp_return_code==0){
//                JSONArray rels = test.getJSONArray("data");
//                for(int i=0;i<rels.length();i++)
//                {
//                    Relation newrel = new Relation();
//                    newrel.setReturn_code(temp_return_code);
//                    newrel.setReturn_message(temp_return_message);
//                    newrel.setinfo(rels.getJSONObject(i),"subclass_of");
//                    test11.add(newrel);
//                }
//            }
//            rel.setrelreturn(temp_return_message, temp_return_code);
//            //rel.setinfo(test, "subclass_of");
//        }
//        if(rel.getReturn_code()==-1) rel.setReturn_message("Please select a type of relation");
//            /*if(rel.getReturn_code()==0){
//                test11.add(rel);
//            }*/
//        if(all_returncode!=0){
//            all_returncode=rel.getReturn_code();
//            all_returnmessage=rel.getReturn_message();
//        }
//
//        String test = com.alibaba.fastjson.JSON.toJSONString(rel);
//        model.addAttribute("rel",test);
//        String testlist = com.alibaba.fastjson.JSON.toJSONString(test11);
//        model.addAttribute("test11",testlist);
//        model.addAttribute("all_returncode",all_returncode);
//        model.addAttribute("all_returnmessage",all_returnmessage);
//
//
//        return new ModelAndView("getRelation", "command", new Relation());
//    }

    @RequestMapping(value = "/input")
    public ModelAndView user() {
        test11=new ArrayList<>();
        all_returncode=-1;
        all_returnmessage="";
        return new ModelAndView("input", "command", new Relation());
    }






    @RequestMapping(value = "/b")
    public Webinfo bbb(String subject){
        System.out.println(subject);
        Webinfo webinfo = new Webinfo();
        webinfo.setHead_entity("aaa");
        webinfo.setTail_entity("111");
        return webinfo;
    }




}
