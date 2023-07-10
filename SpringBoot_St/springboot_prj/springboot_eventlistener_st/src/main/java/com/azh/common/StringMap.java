package com.azh.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class StringMap extends HashMap<String,String> {
    
    public StringMap(String jsonStr){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StringMap dataMap = objectMapper.readValue(jsonStr, StringMap.class);
            this.putAll(dataMap);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public StringMap(){

    }
}
