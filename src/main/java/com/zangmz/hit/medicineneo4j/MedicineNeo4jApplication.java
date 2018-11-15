package com.zangmz.hit.medicineneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.zangmz.hit.medicineneo4j.repositories")
public class MedicineNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineNeo4jApplication.class, args);
	}
}
