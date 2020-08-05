package com.zfs.web.controller;

import com.zfs.web.vo.OrderVO;
import com.zfs.web.service.IOrderService;
import com.zfs.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     *
     * @param draw      draw
     * @param pageNum   页号
     * @param pageSize  页大小
     * @param startDate 支付时间
     * @param endDate   支付时间
     * @param comTypeId 产品类型
     * @param type      支付方式
     * @param uChanId   用户渠道
     * @param sChanId   销售渠道
     * @param phone     支付账号
     * @param number    订单编号
     * @return 一页数据
     */
    @RequestMapping("query")
    public DTPageInfo<OrderVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                     @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                     @RequestParam(value = "startDate") String startDate,
                                     @RequestParam(value = "endDate") String endDate,
                                     @RequestParam(value = "comTypeId") int comTypeId,
                                     @RequestParam(value = "type") int type,
                                     @RequestParam(value = "uChanId") int uChanId,
                                     @RequestParam(value = "sChanId") int sChanId,
                                     @RequestParam(value = "phone") String phone,
                                     @RequestParam(value = "number") String number) throws ParseException {
        Map<String, Object> reqData = new HashMap<>(8);
        reqData.put("startDate", startDate);
        if(null != endDate && !"".equals(endDate)) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            calendar.setTime(df.parse(endDate));
            calendar.add(Calendar.DATE, 1);
            endDate = df.format(calendar.getTime());
        }
        reqData.put("endDate", endDate);
        reqData.put("comTypeId", comTypeId);
        reqData.put("type", type);
        reqData.put("uChanId", uChanId);
        reqData.put("sChanId", sChanId);
        reqData.put("phone", phone);
        reqData.put("number", number);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
