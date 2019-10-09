package com.rpa.web.mapper;

import com.rpa.web.domain.UserActivityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseDAO<UserActivityDO, Integer> {
}