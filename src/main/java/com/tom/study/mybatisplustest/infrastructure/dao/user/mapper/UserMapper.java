package com.tom.study.mybatisplustest.infrastructure.dao.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2025年03月04日 06:46:00
 */
public interface UserMapper extends BaseMapper<UserPO> {

    void updateBalance(@Param(Constants.WRAPPER) LambdaQueryWrapper<UserPO> wrapper, @Param("balance") BigDecimal balance);

    @Update("update user_tbl set balance = balance - #{money} where id = #{id}")
    void updateBalanceV2(@Param("id") long id, @Param("money") BigDecimal money);
}
