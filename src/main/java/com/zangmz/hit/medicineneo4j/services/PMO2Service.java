package com.zangmz.hit.medicineneo4j.services;

import com.zangmz.hit.medicineneo4j.domain.PrimaryClass;
import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.repositories.InstanceRep;
import com.zangmz.hit.medicineneo4j.repositories.KlassRep;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PMO2Service {

    @Resource
    private KlassRep klassRep;

    @Resource
    private InstanceRep instanceRep;

    @Transactional(readOnly = true)
    public Klass getKlassByName(String name) {
        Klass klass = klassRep.getKlassbyName(name);
        System.out.println(klass);
        return klass;
    }

    @Transactional(readOnly = true)
    public Klass testdb() {
        Klass klass = klassRep.test();
        System.out.println(klass);
        return klass;
    }

//    @Transactional(readOnly = true)
//    public Instance testdb(){
//        Instance instance =  instanceRep.test();
//        System.out.println(instance);
//        return instance;
//    }

    public List<Map<String, String>> getTextRelation(File relationText) {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(relationText));


            String tempString = "";
            String sentence = "";
            while ((tempString = reader.readLine()) != null) {
                String info[] = tempString.split("\\|");


                if (info.length > 10) {
                    Map<String, String> relation = new HashMap<>();
                    relation.put("head", info[3]);
                    relation.put("relation", info[8]);
                    relation.put("tail", info[10]);
                    relation.put("sentence", sentence.substring(info[0].length() + 1));
                    results.add(relation);
                } else if (!StringUtils.isEmpty(tempString)) {//保存例句
                    sentence = tempString;
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(results);
        return results;
    }

    public void writeRelation(File infile) {
        List<Map<String, String>> relationList = getTextRelation(infile);
        for (Map<String, String> relation : relationList) {
            Long headId = findId("(!?)" +  relation.get("head"));
            Long tailId = findId("(!?)" + relation.get("tail"));
            System.out.println(headId);
            System.out.println(tailId);
            if (headId != null && tailId != null){
                klassRep.writeRelation(headId, tailId, relation.get("relation"));
            }


        }

    }

    public void writeAllRelation(String path){
        File file = new File(path);
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory()){
                writeRelation(f);
            }

        }

    }

    private Long findId(String word){
        Long id = klassRep.getKlassIdbyName(word);
        if (id == null){
            id = klassRep.getSyKlass(word);
            if(id == null){
                id = instanceRep.getInstanceIdbyName(word);
                if (id == null){
                    id = instanceRep.getSyInstance(word);
                }
            }
        }

        return id;
    }

}
