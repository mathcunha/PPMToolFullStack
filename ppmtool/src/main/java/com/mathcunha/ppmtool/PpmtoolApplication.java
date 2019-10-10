package com.mathcunha.ppmtool;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PpmtoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpmtoolApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner demoSpecialty(ProjectRepository repository) {
		return (args) -> {
			Project project = new Project();
			project.setName("test");
			repository.save(project);
		};
	}*/
}
