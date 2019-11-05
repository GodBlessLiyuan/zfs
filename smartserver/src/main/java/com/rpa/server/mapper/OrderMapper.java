package com.rpa.server.mapper;

import com.rpa.server.bo.OrderBO;
import com.rpa.server.pojo.OrderPO;
import com.rpa.server.pojo.OrderPOKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderMapper继承基类
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO, OrderPOKey> {
    /**
     * 查询UserId的购买信息
     *
     * @param userId
     * @return
     */
    List<OrderBO> queryByUserId(Long userId);

    /**
     * 根据 订单编号查询
     *
     * @param orderNumber
     * @return
     */
    OrderPO queryByOrderNumber(String orderNumber);
}