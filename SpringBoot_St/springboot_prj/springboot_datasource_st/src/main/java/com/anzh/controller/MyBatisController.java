package com.anzh.controller;

import com.anzh.service.MyBatisService;
import com.anzh.vo.ActorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mybatis")
@Slf4j
public class MyBatisController {
    
    private MyBatisService mybatisService;
    
    @Autowired
    public MyBatisController (MyBatisService myBatisService) {
        this.mybatisService = myBatisService;
    }
    
    @PostMapping("/selectActor")
    public ActorVO selectActor(@RequestBody ActorVO actorVO) {
        
    }
}
