package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.tom.study.mybatisplustest.infrastructure.common.enums.UserStateEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserPO.java
 * @Description TODO
 * @createTime 2025年03月04日 06:45:00
 */
@Data
@TableName("user_tbl")
public class UserPO {

    @TableId("id")
    private Long id;

    @TableField("name")
    private String name;

    private String mobilePhone;

    private String addr;

    private BigDecimal balance;

//    private String userState;
    private UserStateEnum userState;

    private String deleted;
}