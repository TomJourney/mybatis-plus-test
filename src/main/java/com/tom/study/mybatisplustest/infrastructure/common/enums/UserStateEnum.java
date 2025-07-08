package com.tom.study.mybatisplustest.infrastructure.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserStateEnum.java
 * @Description TODO
 * @createTime 2025年07月09日 06:43:00
 */
@Getter
@AllArgsConstructor
public enum UserStateEnum {

    ON("1", "在线"),
    OFF("0", "离线");

    @EnumValue
    private final String value;
    private final String desp;

}
