package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

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
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData);
}
