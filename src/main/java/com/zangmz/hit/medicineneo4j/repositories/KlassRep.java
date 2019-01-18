package com.zangmz.hit.medicineneo4j.repositories;

import com.sun.tracing.dtrace.ProviderAttributes;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface KlassRep extends Neo4jRepository<Klass, Long> {

    @Query("MATCH (n:`class`) WHERE n.`name`='Protein' RETURN n")
    Klass test();

    @Query("MATCH (n:`class`) WHERE n.name=~{word} RETURN n limit 1")
    Klass getKlassbyName(@Param("word") String word);

    @Query("MATCH (n:`class`) WHERE n.name=~{word} RETURN ID(n) limit 1")
    Long getKlassIdbyName(@Param("word") String word);

    @Query("MATCH (m:domain_synonym)-[:SY]-(n:`class`) WHERE m.term=~{word} RETURN ID(n) limit 1")
    Long getSyKlass(@Param("word") String word);

    @Query("start a =node({headId}),b=node({tailId}) create (a)-[n:umls_rel {relation:{relation}, sentence:{sentence}}]->(b)  return n")
    Long writeRelation(@Param("headId") Long headId, @Param("tailId") Long tailId, @Param("relation") String relation, @Param("sentence") String sentence);

    @Query("start a =node({headId}),b=node({tailId}) create (a)-[n:umls_pcnn {relation:{relation}, pmid:{pmid}, sentence:{sentence}}]->(b)  return n")
    Long writeRelationPmid(@Param("headId") Long headId, @Param("tailId") Long tailId, @Param("relation") String relation, @Param("sentence") String sentence, @Param("pmid") String pmid);

}
