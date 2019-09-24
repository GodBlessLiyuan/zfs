package com.rpa.web.mapper;

import com.rpa.web.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseDAO<UserActivityPO, Integer> {
}