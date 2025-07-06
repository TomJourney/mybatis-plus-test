package com.tom.study.mybatisplustest.appilcation.user.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserFormDTO.java
 * @Description TODO
 * @createTime 2025年07月06日 07:25:00
 */
@Data
public class UserFormDTO {

    private Long id;

    private String name;

    private String mobilePhone;

    private String addr;

    private BigDecimal balance;

    private String userState;
}