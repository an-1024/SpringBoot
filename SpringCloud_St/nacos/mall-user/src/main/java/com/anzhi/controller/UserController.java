package com.anzhi.controller;

import com.anzhi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URLEncoder;

@RestController
@RequestMapping("/mall_user")
@Slf4j
public class UserController {

    @Resource
    private RestTemplate restTemplate;
    
    @Resource
    private WebClient webClient;
    
    @Resource
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;
    
    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // restTemplate调用,url写死
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        // ribbon实现，restTemplate需要添加@LoadBalanced注解
        // mall-order  ip:port
        String url = "http://mallOrder/mallorder/findOrderByUserId/"+id;

        R result = restTemplate.getForObject(url,R.class);
        return result;
    }

    @RequestMapping(value="/findOrderByUserIdWithWebClient/{id}")
    public Mono<R> findOrderByUserIdWithWebClient(@PathVariable("id")Integer id) {
        String url = "http://mallOrder/mallorder/findOrderByUserId/"+id;
        //基于WebClient
        Mono<R> result = webClient.get().uri(url).retrieve().bodyToMono(R.class);
        
        return result;
    }

    @RequestMapping(value = "/findOrderByUserIdWithWebFlux/{id}")
    public Mono<R> findOrderByUserIdWithWebFlux(@PathVariable("id")Integer id) {
        final String url = "http://mallOrder/mallorder/findOrderByUserId/"+id;
        //基于WebClient+webFlux
        Mono<R> result = WebClient.builder().filter(lbFunction).build()
                .get().uri(url).retrieve().bodyToMono(R.class);
        return result;
    }
}
