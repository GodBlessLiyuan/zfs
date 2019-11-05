package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.OrderPO;
import com.rpa.consumer.pojo.OrderPOKey;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * OrderMapper继承基类
 */
@Mapping
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
}