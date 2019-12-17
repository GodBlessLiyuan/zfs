package com.rpa.front.mapper;

import com.rpa.front.pojo.ActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivityMapper继承基类
 */
@Mapper
public interface ActivityMapper extends BaseMapper<ActivityPO, Integer> {
}