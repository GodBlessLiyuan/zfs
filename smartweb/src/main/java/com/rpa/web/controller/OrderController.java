package com.rpa.web.controller;

import com.rpa.web.dto.OrderDTO;
import com.rpa.web.service.IOrderService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 14:00
 * @description: 订单信息
 * @version: 1.0
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService service;

    /**
     * 订单查询
     * @param draw draw
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param startDate 支付时间
     * @param endDate 支付时间
     * @param comTypeId 产品类型
     * @param type 支付方式
     * @param chanId 渠道
     * @param phone 支付账号
     * @param number 订单编号
     * @return 一页数据
     */
    @RequestMapping("query")
    public DTPageInfo<OrderDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                      @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "startDate") String startDate,
                                      @RequestParam(value = "endDate") String endDate,
                                      @RequestParam(value = "comTypeId") int comTypeId,
                                      @RequestParam(value = "type") int type,
                                      @RequestParam(value = "chanId") int chanId,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "number") String number) {
        Map<String, Object> reqData = new HashMap<>(7);
        reqData.put("startDate", startDate);
        reqData.put("endDate", endDate);
        reqData.put("comTypeId", comTypeId);
        reqData.put("type", type);
        reqData.put("chanId", chanId);
        reqData.put("phone",phone );
        reqData.put("number", number);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
