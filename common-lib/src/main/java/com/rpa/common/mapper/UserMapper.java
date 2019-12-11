package com.rpa.common.mapper;

import com.rpa.common.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    String queryPhoneByUserId(Long userId);

    String queryPhoneByUserid(Long aId);

    int queryTodayNewRegister();
}