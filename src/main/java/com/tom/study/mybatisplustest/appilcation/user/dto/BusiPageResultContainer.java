package com.tom.study.mybatisplustest.appilcation.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName CommonPageQryResult.java
 * @Description TODO
 * @createTime 2025年07月12日 15:09:00
 */
@Data
@AllArgsConstructor(staticName = "of") // 设置静态生成器方法
public class BusiPageResultContainer<T> {
    private Long rdTotal;
    private Long pageTotal;
    private List<T> resultList;
}
