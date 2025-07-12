package com.tom.study.mybatisplustest.appilcation.user.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName CommonPageQryDTO.java
 * @Description 通用分页查询dto
 * @createTime 2025年07月12日 15:07:00
 */
@Data
public class BusiPageQryParam {

    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private String sortBy;
    private Boolean isAsc = Boolean.TRUE;

    public <T> Page<T> toMyBatisPlusPage(OrderItem... orderItems) {
        // 1.1 分页条件
        Page<T> page = Page.of(pageNo, pageSize);
        // 1.2 排序条件
        if (StringUtils.hasText(sortBy)) {
            page.addOrder((new OrderItem()).setColumn(sortBy).setAsc(isAsc));
        } else if (orderItems != null && orderItems.length > 0) {
            // 为空默认传递
            page.addOrder(orderItems);
        }
        return page;
    }

}
