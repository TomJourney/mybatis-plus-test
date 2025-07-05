package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.appilcation.user.service.UserAppService;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(path = "/findUserByCondition01", consumes = "application/json")
    public List<UserPO> findUserByCondition01() {
        return userAppService.qryByCondition01();
    }

    @PostMapping(path = "/updateByCondition2", consumes = "application/json")
    public void updateByCondition2() {
        userAppService.updateByCondition2();
    }

    @PostMapping(path = "/updateByCondition3", consumes = "application/json")
    public void updateByCondition3() {
        userAppService.updateByCondition3();
    }

    @PostMapping(path = "/qryByLambdaCondition04", consumes = "application/json")
    public List<UserPO> qryByLambdaCondition04() {
        return userAppService.qryByLambdaCondition04();
    }

    @PostMapping(path = "/updateBalanceByDiySql", consumes = "application/json")
    public void updateBalanceByDiySql() {
        userAppService.updateBalanceByDiySql();
    }
}
