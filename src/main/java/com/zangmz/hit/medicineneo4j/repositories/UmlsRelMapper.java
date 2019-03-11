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

    public BaseRes<PMO2Relation> get_rel(Long subjectId, Long objectId, String relationType) throws Exception {

        BaseRes<PMO2Relation> resResult = new BaseRes<>();
        List<PMO2Relation> pmo2RelationList = new ArrayList<>();

        try {
            String cmdSql = "match (s) -[rel:"+relationType+"]-> (o) where ID(s)= "+subjectId+" and ID(o) = "+objectId+" return s, o, rel";
            StatementResult result = session.run(cmdSql);
            if (!result.hasNext()){
                resResult.getFail("no relation", "3");
                return resResult;
            }
            while (result.hasNext()) {
                Record record = result.next();
                Klass subject = new Klass();

                PMO2Relation pmo2Relation = new PMO2Relation();
                subject.setId(subjectId);
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
                object.setId(objectId);
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

//                System.out.println(subject);



            }
            resResult.setData(pmo2RelationList);
            resResult.getSucess("search success");
        } catch (Exception e) {
            System.err.println(e.getClass() + "," + e.getMessage());
        }
        return resResult;
    }

}
