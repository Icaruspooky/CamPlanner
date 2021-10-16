package com.seals.camplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

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
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
