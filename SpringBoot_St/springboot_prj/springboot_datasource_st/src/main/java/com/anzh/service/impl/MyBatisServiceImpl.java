package com.anzh.service.impl;

import com.anzh.dao.MyBatisMapper;
import com.anzh.dto.ActorDTO;
import com.anzh.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService {
    
    private MyBatisMapper myBatisMapper;
    
    @Autowired
    public MyBatisServiceImpl (MyBatisMapper myBatisMapper) {
        this.myBatisMapper = myBatisMapper;
    }
    
    @Override
    public List<ActorDTO> selectActor(ActorDTO actorDTO) {
        
        return myBatisMapper.selectActor();
    }
}
