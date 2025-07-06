package com.tom.study.mybatisplustest.application.user.service;

import com.tom.study.mybatisplustest.appilcation.user.service.MyBatisPlusUserService;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAppService.java
 * @Description TODO
 * @createTime 2025年03月04日 08:32:00
 */
@SpringBootTest
public class MyBatisPlusUserBatchInsertServiceTest {

    @Autowired
    private MyBatisPlusUserService userService;

    private UserPO buildUserPO(int i) {
        UserPO userPO = new UserPO();
        userPO.setId(Integer.toUnsignedLong(i));
        userPO.setName("user0706_" + i);
        userPO.setMobilePhone("130" + String.format("08d", i));
        userPO.setAddr("成都市天府六街第" + i + "号");
        userPO.setBalance(new BigDecimal(i));
        return userPO;
    }

    @Test
    void testInsertUserOneByOne() {
        long start = System.currentTimeMillis();
        for (int i = 20000; i < 30000; i++) {
            userService.save(buildUserPO(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("多个循环插入用户，耗时（单位秒） = " + (end - start) / 1000);
        // 多个循环插入用户，耗时（单位秒） = 45
    }

    @Test
    void testBatchInsertUser() {
        long start = System.currentTimeMillis();
        List<UserPO> userPOList = new ArrayList<>();
        int i = 30000;
        for (; i < 40000; i++) {
            userPOList.add(buildUserPO(i));
            // 每1000条作为一个批次插入
            if (i % 1000 == 0) {
                userService.saveBatch(userPOList);
                userPOList.clear();
            }
        }
        if (i % 1000 != 0) {
            userService.saveBatch(userPOList);
        }
        long end = System.currentTimeMillis();
        System.out.println("采用批量插入，耗时（单位秒） = " + (end - start) / 1000);
        // [数据库连接设置*rewriteBatchedStatements=true]采用批量插入，耗时（单位秒） = 3
    }

}
