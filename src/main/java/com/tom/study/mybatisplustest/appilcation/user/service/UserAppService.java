package com.tom.study.mybatisplustest.appilcation.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserMapper;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserAppService.java
 * @Description TODO
 * @createTime 2025年03月04日 08:32:00
 */
@Service
public class UserAppService {

    @Autowired
    UserMapper userMapper;

    public UserPO findUserById(String id) {
        return userMapper.selectById(id);
    }

    public void saveNewUser(UserPO UserPO) {
        userMapper.insert(UserPO);
    }

    public List<UserPO> qryByCondition01() {
        QueryWrapper<UserPO> userPOQueryWrapper = new QueryWrapper<UserPO>()
                .select("id", "name", "addr")
                .like("name", "6")
                .ge("id", 100);
        // 查询
        return userMapper.selectList(userPOQueryWrapper);
    }
//    ==>  Preparing: SELECT id,name,addr FROM user_tbl WHERE (name LIKE ? AND id >= ?)
//     ==> Parameters: %6%(String), 100(Integer)
//            <==    Columns: id, name, addr
//<==        Row: 106, tr106, 成都市天府大道106号
//<==        Row: 116, tr116, 成都市天府大道116号
//<==      Total: 2

    public void updateByCondition2() {
        QueryWrapper<UserPO> updateWrapper = new QueryWrapper<UserPO>()
                .eq("name", "user2");
        UserPO userPO = new UserPO();
        userPO.setAddr("成都天府四街401号");
        userPO.setMobilePhone("110");
        userMapper.update(userPO, updateWrapper);
    }
//    JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@774e182c] will not be managed by Spring
//==>  Preparing: UPDATE user_tbl SET mobile_phone=?, addr=? WHERE (name = ?)
//==> Parameters: 110(String), 成都天府四街401号(String), user2(String)
//            <==    Updates: 1
}
