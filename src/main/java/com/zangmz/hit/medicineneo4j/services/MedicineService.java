package com.zangmz.hit.medicineneo4j.services;

import com.zangmz.hit.medicineneo4j.domain.Concept;
import com.zangmz.hit.medicineneo4j.domain.PrimaryClass;
import com.zangmz.hit.medicineneo4j.repositories.ConceptRep;
import com.zangmz.hit.medicineneo4j.repositories.PrimaryClassRep;
import com.zangmz.hit.medicineneo4j.utils.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {

    private final static Logger LOG = LoggerFactory.getLogger(MedicineService.class);

    @Resource
    private PrimaryClassRep primaryClassRep;

    @Resource
    private ConceptRep conceptRep;

    @Transactional(readOnly = true)
    public PrimaryClass findByName(String name){
        return primaryClassRep.findByName(name);
    }

    @Transactional(readOnly = true)
    public Concept getConceptByWord(String word){
        Concept result = conceptRep.getConceptByWord("(?i)" + word);
        if(result==null){
            result = conceptRep.getConceptByWordBlur(word);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Double getShortestPath(String concept1, String concept2){
        Double result = conceptRep.getShortestPath(concept1,concept2);
        return result;
    }

    @Transactional(readOnly = true)
    public Concept getConceptFromTwoWords(String word1, String word2){
        String regex =  ".*(?i)"+word1+".*(?i)"+word2+".*|.*(?i)"+word2+".*(?i)"+word1+".*";
        return conceptRep.getCombineByWordBlurRegEx(regex);
    }

    public double getSimilarity(String word1, String word2){
        Concept conceptbase = this.getConceptFromTwoWords(word1, word2);
        Double simTemp = 0.0;
        if (conceptbase!=null){//如果两个术语出现在同一概念中
            simTemp = 0.25;
        }
        Concept conceptWord1 = this.getConceptByWord(word1);
        Concept conceptWord2 = this.getConceptByWord(word2);
//        if(conceptWord1 == null || conceptWord2 == null){ //如果有
//            if(simTemp != 0.0){
//                return simTemp;
//            }
//            return 0;
//        }
        if(conceptWord1.getCid().equals(conceptWord2.getCid())){//如果两个术语指向同一个概念
            return 0.5;
        }
        else{
            Double distance = this.getShortestPath(conceptWord1.getCid(), conceptWord2.getCid());
            if (distance==null){

                return simTemp;

            }
            else {
                System.out.println(distance);
                Double simResult;

                simResult = 1/distance;

                return simTemp > simResult ? simTemp : simResult;
            }
        }

    }

//    @Transactional(readOnly = true)
//    public double getSimilarityByConcept(String concept1, String concept2){
//        if(concept1.equals(concept2)){
//            return 1;
//        }
//        else{
//            return 1/this.getShortestPath(concept1, concept2);
//        }
//    }

    public Double getCso(String wordSet, String lib){
        List<String> wordList = WordUtils.splitWordSet(wordSet);
        List<String> wordListUse = new ArrayList<>();
        Double cnpms = 0.0;
        if("medOntology".equals(lib)){ // 精准医学本体
            for(String word : wordList){
                Concept concept = this.getConceptByWord(word);
                if(concept!=null){
                    wordListUse.add(word);
                }
            }
            int len = wordListUse.size();
            Double[][] similarity = new Double[len][len];
            for (int i = 0; i < len; i++){
                for (int j = i+1; j < len; j++){
                    similarity[i][j] = getSimilarity(wordListUse.get(i), wordListUse.get(j));
                }
            }
            Double similaritySum = 0.0;

            for (int i = 0; i < len; i++){
                for(int j = 0; j < len; j++){
                    System.out.print(similarity[i][j]+" ");

                }
                System.out.println();
            }

            for (int i = 0; i < len; i++){
                for(int j = i+1; j < len; j++){
                    similaritySum += similarity[i][j];

                }
            }
            cnpms = 2.0 / (len * (len-1.0)) * similaritySum;

            System.out.println(wordListUse);
            System.out.println(wordListUse.size());
        }
        else if("UMLS".equals(lib)){


        }
        else{
            System.out.println("不能识别的lib");
        }


        return cnpms;
    }






}
