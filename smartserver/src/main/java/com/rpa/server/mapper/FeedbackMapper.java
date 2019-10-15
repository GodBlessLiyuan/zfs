package com.rpa.server.mapper;

import com.rpa.server.pojo.FeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FeedbackMapper继承基类
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<FeedbackPO, Integer> {
}