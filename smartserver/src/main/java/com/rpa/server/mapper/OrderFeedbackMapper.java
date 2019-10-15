package com.rpa.server.mapper;

import com.rpa.server.pojo.OrderFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderFeedbackMapper继承基类
 */
@Mapper
public interface OrderFeedbackMapper extends BaseMapper<OrderFeedbackPO, Long> {
}