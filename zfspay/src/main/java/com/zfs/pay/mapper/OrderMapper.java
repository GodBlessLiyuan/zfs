package com.zfs.pay.mapper;

import com.zfs.pay.pojo.OrderPO;
import com.zfs.pay.pojo.OrderPOKey;
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
    OrderPO queryByOrderNumber(String orderNumber);
}