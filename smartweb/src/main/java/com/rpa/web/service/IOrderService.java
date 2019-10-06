package com.rpa.web.service;

import com.rpa.web.dto.OrderDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 14:01
 * @description: 订单信息
 * @version: 1.0
 */
public interface IOrderService {
    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<OrderDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
