package com.rpa.server.mapper;

import com.rpa.server.pojo.ActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivityMapper继承基类
 */
@Mapper
public interface ActivityMapper extends BaseMapper<ActivityPO, Integer> {
}