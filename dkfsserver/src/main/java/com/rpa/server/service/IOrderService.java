package com.rpa.server.service;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OrderDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 16:46
 * @description: 订单
 * @version: 1.0
 */
public interface IOrderService {
    /**
     * 获取订单信息
     *
     * @param dto
     * @return
     */
    ResultVO getOrders(OrderDTO dto);
}
