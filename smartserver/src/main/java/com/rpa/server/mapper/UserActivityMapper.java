package com.rpa.server.mapper;

import com.rpa.server.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
}