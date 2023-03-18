package com.anzhi.config;

import com.anzhi.annotation.MyLoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfig {
    @Bean
    @MyLoadBalanced// 微服务名替换为具体的ip:port
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public RestTemplate restTemplate1(LoadBalancerInterceptor loadBalancerInterceptor){
        RestTemplate restTemplate = new RestTemplate();
        //注入loadBalancerInterceptor拦截器
        restTemplate.setInterceptors(Arrays.asList(loadBalancerInterceptor));
        return restTemplate;
    }
}
