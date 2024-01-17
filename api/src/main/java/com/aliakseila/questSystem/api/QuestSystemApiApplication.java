package com.aliakseila.questSystem.api;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class QuestSystemApiApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuestSystemApiApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
