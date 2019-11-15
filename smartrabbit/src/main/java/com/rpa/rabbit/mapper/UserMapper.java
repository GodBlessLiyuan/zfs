package com.rpa.rabbit.mapper;

import com.rpa.rabbit.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    int queryTodayNewRegister();
}