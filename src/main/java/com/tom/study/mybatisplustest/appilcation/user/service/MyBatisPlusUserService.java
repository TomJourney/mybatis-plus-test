package com.tom.study.mybatisplustest.appilcation.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.tom.study.mybatisplustest.adapter.user.vo.UserAddrVO;
import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.dto.UserQueryDTO;
import com.tom.study.mybatisplustest.infrastructure.converter.UserAddrConverter;
import com.tom.study.mybatisplustest.infrastructure.converter.UserConverter;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserAddrPO;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserMapper;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAppService.java
 * @Description TODO
 * @createTime 2025年03月04日 08:32:00
 */
@Service
@RequiredArgsConstructor
public class MyBatisPlusUserService extends ServiceImpl<UserMapper, UserPO> {

    private final UserConverter userConverter;
    private final UserAddrConverter userAddrConverter;

    public void deductBalance(long id, BigDecimal money) {
        // 1 查询用户
        UserPO userPO = this.getById(id);
        // 2 校验用户状态
        if (Objects.isNull(userPO) || "0".equals(userPO.getUserState())) {
            throw new RuntimeException("用户状态异常");
        }
        // 3 校验余额是否充足
        if (userPO.getBalance().compareTo(money) < 0) {
            throw new RuntimeException("用户余额不足");
        }
        // 4 扣减余额 update table set balance = balance - money where id = #{id}
        baseMapper.updateBalanceV2(id, money);
        //    ==>  Preparing: update user_tbl set balance = balance - ? where id = ?
        //            ==> Parameters: 150(BigDecimal), 1000(Long)
        //            <==    Updates: 1
    }

    public List<UserPO> queryUserByMultiCondition(UserQueryDTO userQueryDTO) {
        return lambdaQuery()
                .like(userQueryDTO.getName() != null, UserPO::getName, userQueryDTO.getName())
                .eq(userQueryDTO.getUserState() != null, UserPO::getUserState, userQueryDTO.getUserState())
                .gt(userQueryDTO.getMinBalance() != null, UserPO::getBalance, userQueryDTO.getMinBalance())
                .lt(userQueryDTO.getMaxBalance() != null, UserPO::getBalance, userQueryDTO.getMaxBalance())
                .list();

//        ==>  Preparing: SELECT id,name,mobile_phone,addr,balance,user_state FROM user_tbl WHERE (name LIKE ? AND user_state = ? AND balance > ? AND balance < ?)
//               ==> Parameters: %user10%(String), 1(String), 5(BigDecimal), 20000(BigDecimal)
//                <==    Columns: id, name, mobile_phone, addr, balance, user_state
//                <==        Row: 10, user10, 17712340010, 成都天府三街010号, 6.00, 1
//                <==        Row: 110, user101, 17612341010, 成都市天府大道110号, 16.00, 1
//                <==        Row: 1000, user1000, 17712341000, 成都天府三街1000号, 850.00, 1
//                <==      Total: 3
    }


    public void usingLambdaDeductBalance(long id, BigDecimal money) {
        // 1 查询用户
        UserPO userPO = this.getById(id);
        // 2 校验用户状态
        if (Objects.isNull(userPO) || "0".equals(userPO.getUserState())) {
            throw new RuntimeException("用户状态异常");
        }
        // 3 校验余额是否充足
        if (userPO.getBalance().compareTo(money) < 0) {
            throw new RuntimeException("用户余额不足");
        }

        // 4 使用lambda表达式扣减余额 update table set balance = balance - money where id = #{id}
        BigDecimal reducedBalance = userPO.getBalance().subtract(money);
        lambdaUpdate()
                .set(UserPO::getBalance, reducedBalance)
                .set(reducedBalance.equals(BigDecimal.ZERO), UserPO::getUserState, 0)
                .eq(UserPO::getId, id)
                .update();
//        ==>  Preparing: UPDATE user_tbl SET balance=? WHERE (id = ?)
        // ==> Parameters: 0.00(BigDecimal), 10000(Long)
//                <==    Updates: 1
    }

    public UserVO queryUserAndAddrById(Long id) {
        // 1 查询用户
        UserPO userPO = getById(id);
        // 2 查询地址
        List<UserAddrPO> userAddrPOList = Db.lambdaQuery(UserAddrPO.class).eq(UserAddrPO::getUserId, id).list();
        UserVO userVO = userConverter.toUserVO(userPO);
        // 3 封装地址到用户
        if (!CollectionUtils.isEmpty(userAddrPOList)) {
            userVO.setUserAddrVOList(userAddrConverter.toUserAddrVOList(userAddrPOList));
        }
        return userVO;
    }

    public List<UserVO> queryUserAndAddrById(List<Long> ids) {
        // 1 查询用户列表
        List<UserVO> userVOList = userConverter.toUserVOList(listByIds(ids));

        // 2 查询地址列表
        List<Long> dbUserIdList = userVOList.stream().map(UserVO::getId).collect(Collectors.toList());
        List<UserAddrPO> userAddrPOList = Db.lambdaQuery(UserAddrPO.class).in(UserAddrPO::getUserId, dbUserIdList).list();
        // 转为map，其中key为用户id，value为地址vo列表
        Map<Long, List<UserAddrVO>> userIdToUserAddrVOsMap =
                userAddrConverter.toUserAddrVOList(userAddrPOList).stream().collect(Collectors.groupingBy(UserAddrVO::getUserId));
        // 3 封装地址到用户
        if (!CollectionUtils.isEmpty(userVOList)) {
            userVOList.forEach(userVO->{
                userVO.setUserAddrVOList(userIdToUserAddrVOsMap.getOrDefault(userVO.getId(), Collections.emptyList()));
            });
        }
        return userVOList;
    }
}
