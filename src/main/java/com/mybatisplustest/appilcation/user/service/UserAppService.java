package com.mybatisplustest.appilcation.user.service;

import com.mybatisplustest.infrastructure.dao.user.mapper.UserMapper;
import com.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
