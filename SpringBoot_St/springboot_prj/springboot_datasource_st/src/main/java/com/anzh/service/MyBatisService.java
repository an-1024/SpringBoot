package com.anzh.service;

import com.anzh.dto.ActorDTO;

import java.util.List;

public interface MyBatisService {
    /**
     * 查询演员表
     * @return
     */
    default public List<ActorDTO> selectActor(ActorDTO actorDTO) {
        return null;
    }
}
