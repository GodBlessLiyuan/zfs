package com.rpa.front.mapper;

import com.rpa.front.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
}