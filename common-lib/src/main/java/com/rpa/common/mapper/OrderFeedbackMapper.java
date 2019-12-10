package com.rpa.common.mapper;

import com.rpa.common.pojo.OrderFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderFeedbackMapper继承基类
 */
@Mapper
public interface OrderFeedbackMapper extends BaseMapper<OrderFeedbackPO, Long> {
}