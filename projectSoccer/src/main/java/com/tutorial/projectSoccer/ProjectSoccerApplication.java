package com.tutorial.projectSoccer;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectSoccerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSoccerApplication.class, args);
	}

	@Bean
	public GroupedOpenApi groupedOpenApi() {
		return GroupedOpenApi.builder()
				.group("api")
				.packagesToScan("com.tutorial.projectSoccer.controller")
				.build();
	}

}
