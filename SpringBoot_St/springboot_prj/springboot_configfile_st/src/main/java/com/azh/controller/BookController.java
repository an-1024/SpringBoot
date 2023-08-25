package com.azh.controller;

import com.alibaba.fastjson.JSON;
import com.azh.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class BookController {
    @Autowired
    private BookDTO bookDto;

    @GetMapping("/book")
    public String book() {
        System.out.println("--------" + bookDto.toString());
        return bookDto.toString();
    }

    // map 作为入参
    @RequestMapping("/testmapparam")
    public Map<String,Object> book(@RequestBody Map<String,Object> paramMap) {
        System.out.println("--------" + JSON.toJSONString(paramMap));
        return paramMap;
    }
}
