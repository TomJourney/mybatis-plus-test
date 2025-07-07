package com.tom.study.mybatisplustest.adapter.user.vo;

import lombok.Data;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAddrVO.java
 * @Description TODO
 * @createTime 2025年07月07日 22:06:00
 */
@Data
public class UserAddrVO {

    private Long id;

    private Long userId;

    private String addrInfo;

    private String addrType;
}
