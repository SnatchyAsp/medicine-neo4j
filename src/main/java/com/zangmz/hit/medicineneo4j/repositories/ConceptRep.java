package com.zangmz.hit.medicineneo4j.repositories;

import com.zangmz.hit.medicineneo4j.domain.Concept;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ConceptRep extends Neo4jRepository<Concept, Long> {

    @Query("MATCH (m:Term)-[:SY]-(n:Concept) WHERE m.term=~{word} RETURN n limit 1")
    Concept getConceptByWord(@Param("word") String word);

    @Query("MATCH (m:Term)-[:SY]-(n:Concept) WHERE m.term contains {word} RETURN n limit 1")
    Concept getConceptByWordBlur(@Param("word") String word);

    @Query("match (c1:Concept{cid:{concept1}}),(c2:Concept{cid:{concept2}}),p=((c1)-[*1..3]-(c2)) with p,reduce(s=0, r in rels(p)|s+r.weight) as dist return dist order by dist asc limit 1")
    Double getShortestPath(@Param("concept1") String concept1, @Param("concept2") String concept2);

    @Query("MATCH (m:Term)-[:SY]-(n:Concept) WHERE m.term =~ {regex} RETURN n limit 1")
    Concept getCombineByWordBlurRegEx(@Param("regex") String regex);

}
