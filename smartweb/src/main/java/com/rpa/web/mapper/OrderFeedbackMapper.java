package com.rpa.web.mapper;

import com.rpa.web.pojo.OrderFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderFeedbackMapper继承基类
 */
@Mapper
public interface OrderFeedbackMapper extends BaseDAO<OrderFeedbackPO, Long> {
}