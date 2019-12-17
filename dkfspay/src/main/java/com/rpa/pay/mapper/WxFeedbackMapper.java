package com.rpa.pay.mapper;

import com.rpa.pay.pojo.WxFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WxFeedbackMapper继承基类
 */
@Mapper
public interface WxFeedbackMapper extends BaseMapper<WxFeedbackPO, Long> {
}