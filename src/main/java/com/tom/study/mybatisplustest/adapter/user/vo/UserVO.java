package com.tom.study.mybatisplustest.adapter.user.vo;

import com.tom.study.mybatisplustest.infrastructure.common.enums.UserStateEnum;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserInfoPO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserFormDTO.java
 * @Description TODO
 * @createTime 2025年07月06日 07:25:00
 */
@Data
public class UserVO {

    private Long id;

    private String name;

    private String mobilePhone;

    private String addr;

    private BigDecimal balance;

    private UserStateEnum userState;

    private List<UserAddrVO> userAddrVOList;

    private UserInfoPO info;
}