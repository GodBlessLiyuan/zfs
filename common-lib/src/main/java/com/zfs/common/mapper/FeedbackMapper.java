package com.zfs.common.mapper;

import com.zfs.common.pojo.FeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FeedbackMapper继承基类
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<FeedbackPO, Integer> {
}