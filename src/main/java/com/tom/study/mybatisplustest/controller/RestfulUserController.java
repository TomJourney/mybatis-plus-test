package com.tom.study.mybatisplustest.controller;

import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.dto.UserFormDTO;
import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.infrastructure.converter.MapStructUserConverter;
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
@RequestMapping("/restful/user")
@RequiredArgsConstructor
public class RestfulUserController {

    private final MyBatisPlusUserService myBatisPlusUserService;

    private final MapStructUserConverter mapStructUserConverter;

    @PostMapping(path = "/saveUser", consumes = "application/json")
    public void saveUser(@RequestBody UserFormDTO userFormDTO) {
        myBatisPlusUserService.save(mapStructUserConverter.toUserPO(userFormDTO));
    }

    @DeleteMapping(path = "/deleteUser/{id}", consumes = "application/json")
    public void deleteUser(@PathVariable("id") Long id) {
        myBatisPlusUserService.removeById(id);
    }

    @GetMapping(path = "/queryUserById/{id}", consumes = "application/json")
    public UserVO queryUserById(@PathVariable("id") Long id) {
        UserPO userPO = myBatisPlusUserService.getById(id);
        return mapStructUserConverter.toUserVO(userPO);
    }

    @GetMapping(path = "/queryUserByIds/{ids}", consumes = "application/json")
    public List<UserVO> queryUserByIds(@PathVariable("ids") List<Long> ids) {
        List<UserPO> userPOList = myBatisPlusUserService.listByIds(ids);
        return mapStructUserConverter.toUserVOList(userPOList);
    }


}
