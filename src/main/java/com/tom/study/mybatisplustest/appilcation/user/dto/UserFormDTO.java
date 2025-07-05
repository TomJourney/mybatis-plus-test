package com.tom.study.mybatisplustest.appilcation.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "用户表单DTO")
public class UserFormDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("地址，json风格")
    private String addr;

    @ApiModelProperty("账户余额")
    private BigDecimal balance;
}