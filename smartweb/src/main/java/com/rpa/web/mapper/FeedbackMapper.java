package com.rpa.web.mapper;

import com.rpa.web.pojo.FeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FeedbackMapper继承基类
 */
@Mapper
public interface FeedbackMapper extends BaseDAO<FeedbackPO, Integer> {
}