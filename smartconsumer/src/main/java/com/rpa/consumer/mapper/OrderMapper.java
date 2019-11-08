package com.rpa.consumer.mapper;

import com.rpa.consumer.bo.OrderBO;
import com.rpa.consumer.pojo.OrderPO;
import com.rpa.consumer.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
    /**
     * 根据 订单编号查询
     *
     * @param orderNumber
     * @return
     */
    OrderBO queryByOrderNumber(String orderNumber);
}