package com.zangmz.hit.medicineneo4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.zangmz.hit.medicineneo4j.repositories")
@MapperScan(value = {"com.zangmz.hit.medicineneo4j.mapper"})

public class MedicineNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineNeo4jApplication.class, args);
	}


}
