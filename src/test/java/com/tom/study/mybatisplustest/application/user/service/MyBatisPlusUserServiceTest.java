package com.tom.study.mybatisplustest.application.user.service;

import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.infrastructure.common.enums.UserStateEnum;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAppService.java
 * @Description TODO
 * @createTime 2025年03月04日 08:32:00
 */
@SpringBootTest
public class MyBatisPlusUserServiceTest {

    @Autowired
    private MyBatisPlusUserService userService;

    @Test
    void testSaveUser() {
        UserPO userPO = new UserPO();
        userPO.setId(123001L);
        userPO.setName("张三001");
        userPO.setMobilePhone("19912340001");
        userPO.setAddr("成都天府五街001号");
        userService.save(userPO);
    }

    @Test
    void testQuery() {
        List<UserPO> userPOS = userService.listByIds(List.of(1, 2, 3, 4));
        System.out.println(userPOS);
    }

    @Test
    void testLogicDelete() {
        Long id = 103L;
        // 删除
        userService.removeById(id);
        // 查询
        UserPO userPO = userService.getById(id);
        System.out.println(userPO);
    }

    @Test
    void testUserStateEnum() {
        UserPO userPO = userService.getById(104L);
        if (userPO.getUserState() == UserStateEnum.ON) {
            System.out.println("用户在线");
        } else {
            System.out.println("用户离线");
        }
        MybatisEnumTypeHandler mybatisEnumTypeHandler;
        // 用户在线
    }
}
