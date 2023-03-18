package com.anzhi.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置，指定负载均衡策略
 */
@Configuration
public class RibbonConfig {
    
    @Bean
    public IRule ibbonRule(){
        // 指定使用Nacos提供的负载均衡策略(优先调用同一集群的实例，基于随机权重)
        return new NacosRule();
    }
}
