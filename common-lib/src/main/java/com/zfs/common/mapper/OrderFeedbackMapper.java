package com.zfs.common.mapper;

import com.zfs.common.pojo.OrderFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderFeedbackMapper继承基类
 */
@Mapper
public interface OrderFeedbackMapper extends BaseMapper<OrderFeedbackPO, Long> {
}