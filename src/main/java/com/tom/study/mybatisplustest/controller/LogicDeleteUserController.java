package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.infrastructure.converter.UserConverter;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2025年07月06日 07:29:00
 */
@RestController
@RequestMapping("/logic-delete/user")
@RequiredArgsConstructor
public class LogicDeleteUserController {

    private final MyBatisPlusUserService myBatisPlusUserService;

    @GetMapping(path = "/{id}")
    public UserPO logicDeleteUser(@PathVariable("id") Long id) {
        myBatisPlusUserService.removeById(id);
        return myBatisPlusUserService.getById(id);
    }
}
