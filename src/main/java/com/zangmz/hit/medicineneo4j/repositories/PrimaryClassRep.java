package com.zangmz.hit.medicineneo4j.repositories;

import com.zangmz.hit.medicineneo4j.domain.PrimaryClass;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PrimaryClassRep extends Neo4jRepository<PrimaryClass, Long>{

    PrimaryClass findByName(@Param("name") String title);



}
