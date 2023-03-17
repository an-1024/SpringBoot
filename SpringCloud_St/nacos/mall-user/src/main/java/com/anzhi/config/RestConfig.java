package com.anzhi.config;

import com.anzhi.annotation.MyLoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    @MyLoadBalanced// 微服务名替换为具体的ip:port
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
