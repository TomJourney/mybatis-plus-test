package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2025年03月04日 06:46:00
 */
public interface UserMapper extends BaseMapper<UserPO> {

    void updateBalance(@Param("ew") LambdaQueryWrapper<UserPO> wrapper, @Param("balance") BigDecimal balance);

//    void updateBalance2(@Param(Constants.WRAPPER) LambdaQueryWrapper<UserPO> wrapper, @Param("balance") BigDecimal balance);
}
