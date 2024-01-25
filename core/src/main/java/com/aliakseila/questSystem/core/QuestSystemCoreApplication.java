package com.aliakseila.questSystem.core;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@AllArgsConstructor
@EntityScan("com.aliakseila.questSystem.model.*")
public class QuestSystemCoreApplication implements ApplicationRunner {


	public static void main(String[] args) {
		SpringApplication.run(QuestSystemCoreApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
	}
}
