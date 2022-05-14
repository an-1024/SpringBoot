package com.azh.controller;

import com.azh.config.User;
import com.azh.config.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private User user;

    @Autowired
    private Users users;

    @GetMapping("/user")
    public String book() {
        log.info("-------- {}", user.toString());
        log.info("--------", users.toString());
        return user.toString() + users.toString();
    }
}
