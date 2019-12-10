package com.rpa.common.mapper;

import com.rpa.common.pojo.ActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivityMapper继承基类
 */
@Mapper
public interface ActivityMapper extends BaseMapper<ActivityPO, Integer> {
}