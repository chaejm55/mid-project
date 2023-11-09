package com.example.midproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MidProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidProjectApplication.class, args);
    }

}
