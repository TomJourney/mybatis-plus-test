package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.appilcation.user.service.UserAppService;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName MybatisPlusNoteController.java
 */
@RestController
@RequestMapping("/mybatis-plus-note")
public class MybatisPlusNoteController {

    @Autowired
    private UserAppService userAppService;

    @GetMapping("/user/{id}")
    public UserPO findUserById(@PathVariable("id") String id) {
        return userAppService.findUserById(id);
    }

    @PostMapping(path = "/add-user", consumes = "application/json")
    public void addUser(@RequestBody UserPO userPO) {
        userAppService.saveNewUser(userPO);
    }
}
