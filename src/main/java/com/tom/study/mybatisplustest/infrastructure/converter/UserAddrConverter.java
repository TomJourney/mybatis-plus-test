package com.tom.study.mybatisplustest.infrastructure.converter;

import com.tom.study.mybatisplustest.adapter.user.vo.UserAddrVO;
import com.tom.study.mybatisplustest.infrastructure.dao.user.mapper.UserAddrPO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName UserConverter.java
 * @Description TODO
 * @createTime 2025年07月06日 07:33:00
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAddrConverter {

    UserAddrVO toUserAddrVO(UserAddrPO userAddrPO);

    List<UserAddrVO> toUserAddrVOList(List<UserAddrPO> userAddrPOList);
}
