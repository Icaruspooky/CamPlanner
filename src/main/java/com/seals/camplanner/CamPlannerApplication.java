package com.seals.camplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamPlannerApplication.class, args);
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
