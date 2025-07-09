package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserInfoPO.java
 * @Description TODO
 * @createTime 2025年07月09日 21:36:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfoPO {

    private int age;
    private String nikeName;
}
