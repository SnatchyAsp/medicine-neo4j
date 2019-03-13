package com.zangmz.hit.medicineneo4j.repositories;


import com.zangmz.hit.medicineneo4j.bo.res.BaseRes;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import com.zangmz.hit.medicineneo4j.pmo2domain.PMO2Relation;
import com.zangmz.hit.medicineneo4j.pmo2domain.UmlsRel;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmlsRelMapper {

    Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
    private Session session = driver.session();

    /**
     * sql语句查询封装result
     * @param cmdSql
     * @return
     */
    private BaseRes<PMO2Relation> getCmdResult(String cmdSql, BaseRes<PMO2Relation> resResult){
        List<PMO2Relation> pmo2RelationList = new ArrayList<>();

        StatementResult result = session.run(cmdSql);
        if (!result.hasNext()){
            resResult.getFail("no relation", "3");
            return resResult;
        }
        while (result.hasNext()) {
            Record record = result.next();
            Klass subject = new Klass();

            PMO2Relation pmo2Relation = new PMO2Relation();
            if (!StringUtils.isEmpty(record.get("ID(s)").toString())){
                subject.setId(new Long(record.get("ID(s)").toString()));
            }
            if (!StringUtils.isEmpty(record.get("s").get("MCID").asString())){
                subject.setMCID(record.get("s").get("MCID").asString());
            }
            if (!StringUtils.isEmpty(record.get("s").get("MTID").asString())){
                subject.setMTID(record.get("s").get("MTID").asString());
            }
            if (!StringUtils.isEmpty(record.get("s").get("name").asString())){
                subject.setName(record.get("s").get("name").asString());
            }
            if (!StringUtils.isEmpty(record.get("s").get("subclass_of").asString())){
                subject.setSubclass_of(record.get("s").get("subclass_of").asString());
            }
            if (!StringUtils.isEmpty(record.get("s").get("tree_number").asString())){
                subject.setTree_number(record.get("s").get("tree_number").asString());
            }
            pmo2Relation.setSubject(subject);

            Klass object = new Klass();
            if (!StringUtils.isEmpty(record.get("ID(o)").toString())){
                object.setId(new Long(record.get("ID(o)").toString()));
            }
            if (!StringUtils.isEmpty(record.get("o").get("MCID").asString())){
                object.setMCID(record.get("o").get("MCID").asString());
            }
            if (!StringUtils.isEmpty(record.get("o").get("MTID").asString())){
                object.setMTID(record.get("o").get("MTID").asString());
            }
            if (!StringUtils.isEmpty(record.get("o").get("name").asString())){
                object.setName(record.get("o").get("name").asString());
            }
            if (!StringUtils.isEmpty(record.get("o").get("subclass_of").asString())){
                object.setSubclass_of(record.get("o").get("subclass_of").asString());
            }
            if (!StringUtils.isEmpty(record.get("o").get("tree_number").asString())){
                object.setTree_number(record.get("o").get("tree_number").asString());
            }
            pmo2Relation.setObject(object);

            UmlsRel umlsRelation = new UmlsRel();
            if (!StringUtils.isEmpty(record.get("rel").get("ID").asString())){
                umlsRelation.setId(record.get("rel").get("ID").asString());
            }
            if(!StringUtils.isEmpty(record.get("rel").get("sentence").asString())){
                umlsRelation.setSentence(record.get("rel").get("sentence").asString());
            }
            if (!StringUtils.isEmpty(record.get("rel").get("relation").asString())){
                umlsRelation.setRelation(record.get("rel").get("relation").asString());
            }
            if (!StringUtils.isEmpty(record.get("rel").get("pmid").asString())){
                umlsRelation.setPmid(record.get("rel").get("pmid").asString());
            }
            pmo2Relation.setRelation(umlsRelation);
            pmo2RelationList.add(pmo2Relation);

        }
        resResult.setData(pmo2RelationList);
        resResult.getSucess("search success");
        return resResult;
    }

    public BaseRes<PMO2Relation> get_rel(Long subjectId, String relationType) throws Exception{
        BaseRes<PMO2Relation> resResult = new BaseRes<>();

        try{
            String cmdSql = "match (s) -[rel:"+relationType+"]-> (o) where ID(s)= "+subjectId+" return s, ID(s), o, ID(o), rel";
            resResult = getCmdResult(cmdSql, resResult);
        }catch (Exception e) {
            System.err.println(e.getClass() + "," + e.getMessage());
        }
        return resResult;
    }

    public BaseRes<PMO2Relation> get_rel(String relationType, Long objectId) throws Exception{
        BaseRes<PMO2Relation> resResult = new BaseRes<>();

        try{
            String cmdSql = "match (s) -[rel:"+relationType+"]-> (o) where ID(o) = "+objectId+" return s, ID(s), o, ID(o), rel";
            resResult = getCmdResult(cmdSql, resResult);
        }catch (Exception e) {
            System.err.println(e.getClass() + "," + e.getMessage());
        }
        return resResult;
    }

    public BaseRes<PMO2Relation> get_rel(Long subjectId, Long objectId, String relationType) throws Exception {

        BaseRes<PMO2Relation> resResult = new BaseRes<>();
        List<PMO2Relation> pmo2RelationList = new ArrayList<>();

        try {
            String cmdSql = "match (s) -[rel:"+relationType+"]-> (o) where ID(s)= "+subjectId+" and ID(o) = "+objectId+" return s, ID(s), o, ID(o), rel";
//            StatementResult result = session.run(cmdSql);
//            if (!result.hasNext()){
//                resResult.getFail("no relation", "3");
//                return resResult;
//            }
//            while (result.hasNext()) {
//                Record record = result.next();
//                Klass subject = new Klass();
//
//                PMO2Relation pmo2Relation = new PMO2Relation();
//                subject.setId(subjectId);
//                if (!StringUtils.isEmpty(record.get("s").get("MCID").asString())){
//                    subject.setMCID(record.get("s").get("MCID").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("s").get("MTID").asString())){
//                    subject.setMTID(record.get("s").get("MTID").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("s").get("name").asString())){
//                    subject.setName(record.get("s").get("name").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("s").get("subclass_of").asString())){
//                    subject.setSubclass_of(record.get("s").get("subclass_of").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("s").get("tree_number").asString())){
//                    subject.setTree_number(record.get("s").get("tree_number").asString());
//                }
//                pmo2Relation.setSubject(subject);
//
//                Klass object = new Klass();
//                object.setId(objectId);
//                if (!StringUtils.isEmpty(record.get("o").get("MCID").asString())){
//                    object.setMCID(record.get("o").get("MCID").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("o").get("MTID").asString())){
//                    object.setMTID(record.get("o").get("MTID").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("o").get("name").asString())){
//                    object.setName(record.get("o").get("name").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("o").get("subclass_of").asString())){
//                    object.setSubclass_of(record.get("o").get("subclass_of").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("o").get("tree_number").asString())){
//                    object.setTree_number(record.get("o").get("tree_number").asString());
//                }
//                pmo2Relation.setObject(object);
//
//                UmlsRel umlsRelation = new UmlsRel();
//                if (!StringUtils.isEmpty(record.get("rel").get("ID").asString())){
//                    umlsRelation.setId(record.get("rel").get("ID").asString());
//                }
//                if(!StringUtils.isEmpty(record.get("rel").get("sentence").asString())){
//                    umlsRelation.setSentence(record.get("rel").get("sentence").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("rel").get("relation").asString())){
//                    umlsRelation.setRelation(record.get("rel").get("relation").asString());
//                }
//                if (!StringUtils.isEmpty(record.get("rel").get("pmid").asString())){
//                    umlsRelation.setPmid(record.get("rel").get("pmid").asString());
//                }
//                pmo2Relation.setRelation(umlsRelation);
//                pmo2RelationList.add(pmo2Relation);
//
//            }
//            resResult.setData(pmo2RelationList);
//            resResult.getSucess("search success");
            resResult = getCmdResult(cmdSql, resResult);
            System.out.println(resResult);
        } catch (Exception e) {
            System.err.println(e.getClass() + "," + e.getMessage());
        }
        return resResult;
    }

}
