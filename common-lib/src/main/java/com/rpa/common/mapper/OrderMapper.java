package com.rpa.common.mapper;

import com.rpa.common.pojo.OrderPO;
import com.rpa.common.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
}