package com.tom.study.mybatisplustest.appilcation.user.dto;

import lombok.Data;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName CommonPageQryDTO.java
 * @Description 通用分页查询dto
 * @createTime 2025年07月12日 15:07:00
 */
@Data
public class BusiPageQryParam {

    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private Boolean isAsc;
}
