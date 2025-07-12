package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.dto.BusiPageResultContainer;
import com.tom.study.mybatisplustest.appilcation.user.dto.UserQueryDTO;
import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.appilcation.user.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName MybatisPlusNoteController.java
 */
@RestController
@RequestMapping("/user/page-query")
public class MybatisPlusPageQueryController {

    @Autowired
    private MyBatisPlusUserService myBatisPlusUserService;

    @PostMapping(path = "/pageQueryUser", consumes = "application/json")
    public BusiPageResultContainer<UserVO> queryUserByPage(@RequestBody UserQueryDTO userQueryDTO) {
        return myBatisPlusUserService.pageUserByPage(userQueryDTO);
    }
}