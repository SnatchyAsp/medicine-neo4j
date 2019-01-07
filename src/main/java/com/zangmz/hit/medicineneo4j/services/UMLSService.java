package com.zangmz.hit.medicineneo4j.services;

import com.alibaba.fastjson.JSON;
import com.zangmz.hit.medicineneo4j.domain.*;
import com.zangmz.hit.medicineneo4j.mapper.PredicationAuxMapper;
import com.zangmz.hit.medicineneo4j.mapper.PredicationMapper;
import com.zangmz.hit.medicineneo4j.mapper.SentenceMapper;
import com.zangmz.hit.medicineneo4j.utils.RestTicketClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UMLSService {

    @Resource
    PredicationMapper predicationMapper;

    @Resource
    PredicationAuxMapper predicationAuxMapper;

    @Resource
    SentenceMapper sentenceMapper;

    RestTicketClient restTicketClient = new RestTicketClient();

    public String getTgt(){
        return restTicketClient.getTgt();
    }

    public Predication getPredicationById(int id){
        return predicationMapper.selectByPrimaryKey(id);
    }

    public Boolean checkRelation(List<String> relationList, String relation){
        Boolean flag = false;
        for (String r: relationList){
            if (r.equals(relation)){
                flag = true;
            }
        }
        return flag;
    }


    public void getPredicationBatch(int train_start, int train_finish, int test_start, int test_finish){
        int train_num = 0;
        int test_num = 0;
        int rel_num = 0;

        int num = train_start;

        Map<String, Integer> relationMap = new HashMap<>();
        relationMap.put("NA", rel_num);
        rel_num++;
        List<String> relationList = new ArrayList<>();
        relationList.add("NA");

        List<RelationInput> relationInputList = new ArrayList<>();

        while(num < train_finish){
            num ++;
            RelationInput relationInput = getRelation(num);
            if ((relationInput != null)){
                relationInputList.add(relationInput);
                train_num++;


                if (!checkRelation(relationList, relationInput.getRelation())){
                    relationMap.put(relationInput.getRelation(), rel_num);
                    relationList.add(relationInput.getRelation());
                    System.out.println(relationInput);
                    rel_num++;
                }
            }
        }

        int num1 = test_start;
        List<RelationInput> relationInputTestList = new ArrayList<>();
        while(num1 < test_finish){
            num1 ++;
            RelationInput relationInput = getRelation(num1);
            if ((relationInput != null)){
                relationInputTestList.add(relationInput);
                test_num++;
                if (!checkRelation(relationList, relationInput.getRelation())){
                    relationMap.put(relationInput.getRelation(), rel_num);
                    relationList.add(relationInput.getRelation());
                    System.out.println(relationInput);
                    rel_num++;
                }
            }
        }

        writeJson(relationInputList, "E:\\zangmz\\search_result\\train.json");
        writeJson(relationInputTestList, "E:\\zangmz\\search_result\\test.json");
        writeJson(relationMap, "E:\\zangmz\\search_result\\rel2id.json");

        String text = "train number: "+ train_num + "\n" + "test number: " + test_num + "\n"+
                "rel number: " + rel_num;

        writeParamJson(text, "E:\\zangmz\\search_result\\text.txt");

        writeText(relationInputList, "E:\\zangmz\\search_result\\train.txt", relationMap);
        writeText(relationInputTestList, "E:\\zangmz\\search_result\\test.txt", relationMap);

    }

    public void writeText(List<RelationInput> list, String path, Map<String, Integer> relationMap){
        File relationDoc = new File(path);
        BufferedWriter outRelationDoc = null;
        try {
            outRelationDoc = new BufferedWriter(new FileWriter(relationDoc));

            for (RelationInput relation : list){
                outRelationDoc.write(relation.getHead().getId()+ "#" + relation.getTail().getId() + ": " + relationMap.get(relation.getRelation()) +"\n");
            }

            outRelationDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeParamJson(String text, String path){
        File relationDoc = new File(path);
        BufferedWriter outRelationDoc = null;
        try {
            outRelationDoc = new BufferedWriter(new FileWriter(relationDoc));

            outRelationDoc.write(text);
            outRelationDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(Object object, String path){
        File relationDoc = new File(path);
        BufferedWriter outRelationDoc = null;
        try {
            outRelationDoc = new BufferedWriter(new FileWriter(relationDoc));
            String json_out = JSON.toJSONString(object);
            outRelationDoc.write(json_out);
            outRelationDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public RelationInput getRelation(int id){

        RelationInput relationInput = new RelationInput();
        Predication predication = predicationMapper.selectByPrimaryKey(id);

        if(predication != null){
            int sentenceId = predication.getSentenceId();
            //        if (sentenceId > 7664820){
//            return null;
//        }

            PredicationAux predicationAux = getAux(predication.getPredicationId());
            if (predicationAux == null){
                return null;
            }

            Sentence sentence = getSentence(sentenceId);
            if (sentence == null){
                return null;
            }

            HeadInput headInput = new HeadInput();
            headInput.setId(predication.getSubjectCui());
            headInput.setType(predication.getSubjectName());
            headInput.setWord(predicationAux.getSubjectText());
            HeadInput tailInput = new HeadInput();
            tailInput.setId(predication.getObjectCui());
            tailInput.setType(predication.getObjectName());
            tailInput.setWord(predicationAux.getObjectText());
            relationInput.setSentence(sentence.getSentence());
            relationInput.setHead(headInput);
            relationInput.setTail(tailInput);
            relationInput.setRelation(predication.getPredicate());

            return relationInput;
        }
        return null;

    }

    public Sentence getSentence(int id){
        return sentenceMapper.selectByPrimaryKey(id);
    }

    public PredicationAux getAux(int id){
        return predicationAuxMapper.selectByPredicationId(id);
    }

}
