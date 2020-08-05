package com.rpa.rabbit.mapper;

import com.rpa.rabbit.bo.OrderBO;
import com.rpa.rabbit.pojo.OrderPO;
import com.rpa.rabbit.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    Long queryDayRevenue();

    int queryPayCount();

    Long queryMonthRevenue();

    /**
     * 查询支付完成但尚未分成的数据
     * @param orderId
     * @return
     */
    List<OrderPO> queryNonRevenue(int orderId);

    /**
     * 查询当前最大OrderId
     * @return
     */
    Integer queryMaxOrderId(int orderId);
}