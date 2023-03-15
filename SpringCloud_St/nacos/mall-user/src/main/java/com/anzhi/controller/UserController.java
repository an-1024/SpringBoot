package com.anzhi.controller;

import com.anzhi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mall_user")
@Slf4j
public class UserController {

    @Resource
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // restTemplate调用,url写死
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        // ribbon实现，restTemplate需要添加@LoadBalanced注解
        // mall-order  ip:port
        String url = "http://mall-order/mallorder/findOrderByUserId/"+id;

        R result = restTemplate.getForObject(url,R.class);
        return result;
    }
}
