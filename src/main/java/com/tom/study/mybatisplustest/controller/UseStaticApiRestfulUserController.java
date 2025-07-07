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
@RequestMapping("/staticdb/restful/user")
@RequiredArgsConstructor
public class UseStaticApiRestfulUserController {

    private final MyBatisPlusUserService myBatisPlusUserService;

    private final UserConverter userConverter;


    @GetMapping(path = "/queryUserById/{id}", consumes = "application/json")
    public UserVO queryUserById(@PathVariable("id") Long id) {
        return myBatisPlusUserService.queryUserAndAddrById(id);
    }

    @GetMapping(path = "/queryUserByIds", consumes = "application/json")
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids) {
        return myBatisPlusUserService.queryUserAndAddrById(ids);
    }
}
