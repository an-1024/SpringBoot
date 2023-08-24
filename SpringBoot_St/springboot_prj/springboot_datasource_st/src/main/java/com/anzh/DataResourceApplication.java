package com.anzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataResourceApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DataResourceApplication.class);
        application.run(args);
    }
}
