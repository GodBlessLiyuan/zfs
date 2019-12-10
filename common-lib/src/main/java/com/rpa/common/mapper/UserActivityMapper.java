package com.rpa.common.mapper;

import com.rpa.common.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
}