package com.azh.controller;

import com.azh.dto.UserVO;
import com.azh.dto.UsersVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserVO user;

    @Autowired
    private UsersVO usersVO;

    @GetMapping("/uservo")
    public String book() {
        log.info("-------- {}", user.toString());
        log.info("--------", usersVO.toString());
        return user.toString() + usersVO.toString();
    }
}
