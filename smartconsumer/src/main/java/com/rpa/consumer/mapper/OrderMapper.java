package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.OrderPO;
import com.rpa.consumer.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
}