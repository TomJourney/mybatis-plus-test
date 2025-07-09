package com.tom.study.mybatisplustest.application.user.service;

import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class MyBatisPlusPageQueryTest {

    @Autowired
    private MyBatisPlusUserService userService;

    @Test
    void testPageQuery() {
        // 1 分页查询
        int pageNo = 1, pageSize = 5;
        // 1.1 分页参数
        Page<UserPO> pageParamDTO = Page.of(pageNo, pageSize);
        // 1.2 排序参数，通过 OrderItem 来指定
        pageParamDTO.addOrder(OrderItem.ascs("id", "balance"));
        // 1.3 分页查询
        Page<UserPO> pageQryResult = userService.page(pageParamDTO);

        // 2 总条数与总页数
        System.out.println("总条数 = " +pageQryResult.getTotal() + ", 总页数 = " + pageQryResult.getPages());

        // 3 分页数据
        List<UserPO> records = pageQryResult.getRecords();
        System.out.println("分页数据 = " + records);
    }
//==>  Preparing: SELECT COUNT(*) AS total FROM user_tbl WHERE deleted = '0'
//            ==> Parameters:
//            <==    Columns: total
//<==        Row: 19024
//            <==      Total: 1
//            ==>  Preparing: SELECT id, name, mobile_phone, addr, balance, user_state, deleted, info FROM user_tbl WHERE deleted = '0' ORDER BY balance ASC LIMIT ?
//            ==> Parameters: 5(Long)
}
