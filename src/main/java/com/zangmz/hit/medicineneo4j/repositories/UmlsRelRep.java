package com.zangmz.hit.medicineneo4j.repositories;

import com.zangmz.hit.medicineneo4j.pmo2domain.UmlsRel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UmlsRelRep extends Neo4jRepository<UmlsRel, Long> {

    @Query("match (s) -[rel:umls_rel]-> (o) where ID(s)= 531787 and ID(o) = 3298 return rel")
    List<UmlsRel> getUmlsRel(@Param("subject") String subject, @Param("object") String object);

    @Query("match (s) -[rel:umls_rel]-> (o) where ID(s)= 531787 and ID(o) = 3298 return rel limit 1")
    UmlsRel test();


}
