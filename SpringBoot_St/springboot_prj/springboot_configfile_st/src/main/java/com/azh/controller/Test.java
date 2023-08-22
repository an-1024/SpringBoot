package com.azh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@RestController
public class Test {
    @Resource
    private BookController bookController;
    
    @PostMapping("/test")
    public String test(@RequestBody Map<String,Object> req) {
        //String jsonStr = URLDecoder.decode(req, "utf-8");
        //Map<String,Object> map = JSON.parseObject(req, new TypeReference<Map<String,Object>>(){});
        return bookController.book();
    }
}
