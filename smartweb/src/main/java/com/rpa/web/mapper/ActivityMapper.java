package com.rpa.web.mapper;

import com.rpa.web.pojo.ActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivityMapper继承基类
 */
@Mapper
public interface ActivityMapper extends BaseDAO<ActivityPO, Integer> {
}