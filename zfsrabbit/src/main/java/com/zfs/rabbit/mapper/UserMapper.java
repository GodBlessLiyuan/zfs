package com.zfs.rabbit.mapper;

import com.zfs.rabbit.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    int queryTodayNewRegister();
}