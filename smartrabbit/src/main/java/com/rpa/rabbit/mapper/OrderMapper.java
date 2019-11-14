package com.rpa.rabbit.mapper;

import com.rpa.rabbit.bo.OrderBO;
import com.rpa.rabbit.pojo.OrderPO;
import com.rpa.rabbit.pojo.OrderPOKey;
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