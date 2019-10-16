package com.mathcunha.ppmtool;

import com.mathcunha.ppmtool.domain.PhotoStatistics;
import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigInteger;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class PpmtoolApplication {

	private static final Logger log = LoggerFactory.getLogger(PpmtoolApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PpmtoolApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoPhoto(PhotoRepository repository) {
		return (args) -> {
			repository.findPhotoCount().forEach(photo -> log.info(photo.toString()));
			Map<String, Object> map = repository.findMaxPhotoCount();
			PhotoStatistics stats = new PhotoStatistics((String) map.get("owner"), ((BigInteger)map.get("total")).longValue());
			log.info(stats.toString());
		};
	}
}
