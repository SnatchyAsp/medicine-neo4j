package com.zangmz.hit.medicineneo4j.repositories;

import com.zangmz.hit.medicineneo4j.pmo2domain.Instance;
import com.zangmz.hit.medicineneo4j.pmo2domain.Klass;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface InstanceRep extends Neo4jRepository<Instance, Long> {

    @Query("MATCH (n:`instance`) WHERE n.`name`='4-Fluorophenylalanine' RETURN n")
    Instance test();

    @Query("MATCH (m:instance_synonym)-[:SY]-(n:`instance`) WHERE m.term=~{word} RETURN ID(n) limit 1")
    Long getSyInstance(@Param("word") String word);

    @Query("MATCH (n:`instance`) WHERE n.name=~{word} RETURN ID(n) limit 1")
    Long getInstanceIdbyName(@Param("word") String word);

}
