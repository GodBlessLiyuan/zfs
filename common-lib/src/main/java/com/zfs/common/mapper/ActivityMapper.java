package com.zfs.common.mapper;

import com.zfs.common.pojo.ActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ActivityMapper继承基类
 */
@Mapper
public interface ActivityMapper extends BaseMapper<ActivityPO, Integer> {
}