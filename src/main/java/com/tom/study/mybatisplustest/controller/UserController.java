package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.dto.UserFormDTO;
import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.infrastructure.dao.user.converter.UserConverter;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MyBatisPlusUserService myBatisPlusUserService;

    private final UserConverter userConverter;

    @ApiOperation("新增用户")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO) {
        myBatisPlusUserService.save(userConverter.toUserPO(userFormDTO));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    public void deleteUser(@ApiParam("用户id") @PathVariable("id") Long id) {
        myBatisPlusUserService.removeById(id);
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable("id") Long id) {
        UserPO userPO = myBatisPlusUserService.getById(id);
        return userConverter.toUserVO(userPO);
    }

    @ApiOperation("根据id批量查询用户")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @PathVariable("ids") List<Long> ids) {
        List<UserPO> userPOList = myBatisPlusUserService.listByIds(ids);
        return userConverter.toUserVOList(userPOList);
    }


}
