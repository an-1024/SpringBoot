package com.anzh;

import com.azh.config.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterceptorApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(InterceptorApplication.class);
        application.setBanner(new MyBanner());
        application.run();
    }
}
