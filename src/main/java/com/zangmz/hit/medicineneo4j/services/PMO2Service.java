package com.zangmz.hit.medicineneo4j.services;

import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.bo.res.RelationListRes;
import com.zangmz.hit.medicineneo4j.domain.PrimaryClass;
import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.pmo2domain.UmlsRel;
import com.zangmz.hit.medicineneo4j.repositories.InstanceRep;
import com.zangmz.hit.medicineneo4j.repositories.KlassRep;
import com.zangmz.hit.medicineneo4j.repositories.UmlsRelMapper;
import com.zangmz.hit.medicineneo4j.repositories.UmlsRelRep;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.*;

@Service
public class PMO2Service {

    @Resource
    private KlassRep klassRep;

    @Resource
    private InstanceRep instanceRep;

    @Resource
    private UmlsRelRep umlsRelRep;


    private UmlsRelMapper umlsRelMapper = new UmlsRelMapper();

    private List<Long> initpath = Arrays.asList(new Long(1614),new Long(2336),new Long(2373),new Long(2671),new Long(2738));

    //1614 2336 2373 2671 2738

    @Transactional(readOnly = true)
    public BaseRes<PMO2Relation> getRelationBySubject(String subject, String relationType){
        BaseRes<PMO2Relation> result = null;
        Long subjectId = findId(subject);
        if (subjectId == null){
            result = new BaseRes<>();
            result.getFail("no subject", "1");
            return result;
        }
        try {
            result = umlsRelMapper.get_rel(subjectId, relationType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Transactional(readOnly = true)
    public BaseRes<PMO2Relation> getRelationBySubjectAndObject(String subject, String object, String relationType){

        BaseRes<PMO2Relation> result = null;
        if ("###".equals(object)){
            Long subjectId = findId(subject);
            if (subjectId == null){
                result = new BaseRes<>();
                result.getFail("no subject", "1");
                return result;
            }
            try {
                result = umlsRelMapper.get_rel(subjectId, relationType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ("###".equals(subject)){
            Long objectId = findId(object);
            if (objectId == null){
                result = new BaseRes<>();
                result.getFail("no object", "2");
                return result;
            }
            try {
                result = umlsRelMapper.get_rel(relationType, objectId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Long subjectId = findId(subject);
            if (subjectId == null){
                result = new BaseRes<>();
                result.getFail("no subject", "1");
                return result;
            }
            Long objectId = findId(object);
            if (objectId == null){
                result = new BaseRes<>();
                result.getFail("no object", "2");
                return result;
            }
            try {
                result = umlsRelMapper.get_rel(subjectId, objectId, relationType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        List<UmlsRel> umlsRelList = umlsRelRep.getUmlsRel(subject, object);

//        UmlsRel umlsRel= umlsRelRep.test();
//        System.out.println(umlsRel);
        return result;


    }


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

    public void writeRelationPred(File relationText) {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(relationText));
            String tempString = "";
            while((tempString = reader.readLine())!= null){
                String info[] = tempString.split("\\|");
                Map<String, String> relation = new HashMap<>();
                //过滤出字母数字
                relation.put("head", info[1].replaceAll("[^(A-Z0-9a-z\\s)]", ""));
                relation.put("relation", info[4]);
                relation.put("tail", info[3].replaceAll("[^(A-Z0-9a-z\\s)]", ""));
                relation.put("sentence", info[5]);
                relation.put("pmid", info[6]);
                results.add(relation);
            }
            System.out.println(results);
            for (Map<String, String> relation : results) {
                Long headId = findId("(?i)" + relation.get("head"));
                Long tailId = findId("(?i)" + relation.get("tail"));
                System.out.println(headId);
                System.out.println(tailId);
                if (headId != null && tailId != null) {
                    klassRep.writeRelationPmid(headId, tailId, relation.get("relation"), relation.get("sentence"), relation.get("pmid"));
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    //过滤出字母数字
                    relation.put("head", info[3].replaceAll("[^(A-Z0-9a-z\\s)]", ""));
                    relation.put("relation", info[8]);
                    relation.put("tail", info[10].replaceAll("[^(A-Z0-9a-z\\s)]", ""));
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
            Long headId = findId("(?i)" + relation.get("head"));
            Long tailId = findId("(?i)" + relation.get("tail"));
            System.out.println(headId);
            System.out.println(tailId);
            System.out.println(infile.getName());
            if (headId != null && tailId != null) {
                klassRep.writeRelation(headId, tailId, relation.get("relation"), relation.get("sentence"));
            }


        }

    }

    public void writeAllRelation(String path) {
        File file = new File(path);
        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
        for (File f : fs) {                    //遍历File[]数组
            if (!f.isDirectory()) {
                writeRelation(f);
            }

        }

    }

    private Long findId(String word) {
        Long id = klassRep.getKlassIdbyName(word);
        if (id == null) {
            id = klassRep.getSyKlass(word);
            if (id == null) {
                id = instanceRep.getInstanceIdbyName(word);
                if (id == null) {
                    id = instanceRep.getSyInstance(word);
                }
            }
        }

        return id;
    }

    @Transactional(readOnly = true)
    public String getNodes(String id){
        String tree = "[";
        if(id.equals("0")){
            System.out.println("id==0");
            List<Klass> nodes = new ArrayList<Klass>();
            Long nid = new Long(0);
            String color="";
            for(int i=1;i<11;i++){
                String treeno = "T"+i+"$";
                System.out.println(treeno);
                Klass troot = klassRep.getKlassbyTreeNo(treeno);
                nodes.add(troot);

            }
            List<Klass> snodes = new ArrayList<Klass>();
            Long pid = new Long(0);
            for(int j = 0;j<5;j++){
                nid = initpath.get(j);
                for(Klass k:nodes){
                    if(klassRep.getrelation(k.getId()) != null){
                        color = "#000000";
                    }
                    else {
                        color = "#C0C0C0";
                    }

                //System.out.println(troot.toString());
                    if(j == 0){
                        tree = tree+"{id:\"t"+k.getId().toString()+"\",pId:\"0\",name:\""+k.getName()+"\",isParent:true,font:{'color':'"+color+"'}";
                    }
                    else {
                        tree = tree+"{id:\"t"+k.getId().toString()+"\",pId:\"t"+pid.toString()+"\",name:\""+k.getName()+"\",isParent:true,font:{'color':'"+color+"'}";
                    }
                    if(k.getId().equals(nid)){
                        snodes =  klassRep.getnode(nid);
                        tree = tree+",open:true},";
                    }
                    else {
                        tree = tree+"},";
                    }

                }
                nodes = snodes;
                pid = nid;
            }

            tree = tree.substring(0,tree.length()-1)+"]";
            System.out.println(tree);
            return tree;
        }
        else {
            id = id.substring(1);
            List<Klass> subnode = klassRep.getnode(Long.parseLong(id));
            int len = subnode.size();
            int i = 0;
            String color="";
            for(i = 0;i<len;i++){

                if(klassRep.getrelation(subnode.get(i).getId()) != null){
                    //System.out.println(klassRep.getrelation(subnode.get(i).getId()));
                    color = "#000000";
                }
                else {
                    color = "#C0C0C0";
                }
                tree = tree+"{id:\"t"+subnode.get(i).getId().toString()+"\",name:\""+subnode.get(i).getName()+"\",isParent:true,font:{'color':'"+color+"'}}";
                if(i!=len-1){
                    tree=tree+",";
                }
            }
            tree = tree+"]";
            System.out.println(tree);
            return tree;
        }
    }

}
