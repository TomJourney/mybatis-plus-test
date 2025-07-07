package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAddrVO.java
 * @Description TODO
 * @createTime 2025年07月07日 22:06:00
 */
@Data
@TableName("user_addr_tbl")
public class UserAddrPO {

    private Long id;

    private Long userId;

    private String addrInfo;

    private String addrType;
}
