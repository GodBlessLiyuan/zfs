package com.rpa.common.mapper;

import com.rpa.common.pojo.FeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FeedbackMapper继承基类
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<FeedbackPO, Integer> {
}