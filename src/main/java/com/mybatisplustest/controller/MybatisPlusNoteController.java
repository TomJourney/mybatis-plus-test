package com.mybatisplustest.controller;

import com.mybatisplustest.appilcation.user.service.UserAppService;
import com.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName SpringbootRedisController.java
 * @Description TODO
 * @createTime 2024年12月01日 16:18:00
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
