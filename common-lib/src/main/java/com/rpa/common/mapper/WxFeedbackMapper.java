package com.rpa.common.mapper;

import com.rpa.common.pojo.WxFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WxFeedbackMapper继承基类
 */
@Mapper
public interface WxFeedbackMapper extends BaseMapper<WxFeedbackPO, Long> {
}