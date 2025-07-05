package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private Long id;
    private String name;
    private String mobilePhone;
    private String addr;
}