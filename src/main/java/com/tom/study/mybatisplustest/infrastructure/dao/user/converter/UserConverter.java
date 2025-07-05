package com.tom.study.mybatisplustest.infrastructure.dao.user.converter;

import com.tom.study.mybatisplustest.adapter.user.vo.UserVO;
import com.tom.study.mybatisplustest.appilcation.user.dto.UserFormDTO;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserConverter.java
 * @Description TODO
 * @createTime 2025年07月06日 07:33:00
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserPO toUserPO(UserFormDTO userFormDTO);

    UserFormDTO toUserFormDTO(UserPO userPO);

    UserVO toUserVO(UserPO userPO);

    List<UserVO> toUserVOList(List<UserPO> userPOList);
}
