package com.rpa.server.mapper;

import com.rpa.server.pojo.OrderPO;
import com.rpa.server.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
}