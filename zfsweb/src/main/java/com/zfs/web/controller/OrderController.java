package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IOrderService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "startDate", required = false) String startDate,
                          @RequestParam(value = "endDate", required = false) String endDate,
                          @RequestParam(value = "comTypeId", required = false) Integer comTypeId,
                          @RequestParam(value = "type", required = false) Integer type,
                          @RequestParam(value = "uChanId", required = false) Integer uChanId,
                          @RequestParam(value = "sChanId", required = false) Integer sChanId,
                          @RequestParam(value = "phone", required = false) String phone,
                          @RequestParam(value = "number", required = false) String number) throws ParseException {
        Map<String, Object> reqData = new HashMap<>(8);
        reqData.put("startDate", startDate);
        if (!StringUtils.isEmpty(endDate)) {
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

        return service.query(pageNum, pageSize, reqData);
    }

    @RequestMapping("export")
    public ResultVO export(@RequestParam(value = "startDate", required = false) String startDate,
                           @RequestParam(value = "endDate", required = false) String endDate,
                           @RequestParam(value = "comTypeId", required = false) Integer comTypeId,
                           @RequestParam(value = "type", required = false) Integer type,
                           @RequestParam(value = "uChanId", required = false) Integer uChanId,
                           @RequestParam(value = "sChanId", required = false) Integer sChanId,
                           @RequestParam(value = "phone", required = false) String phone,
                           @RequestParam(value = "number", required = false) String number,
                           HttpServletResponse response) throws ParseException {
        Map<String, Object> reqData = new HashMap<>(8);
        reqData.put("startDate", startDate);
        //重新将结束时间加上一天
        if (!StringUtils.isEmpty(endDate)) {
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

        return service.export(reqData,response);
    }
}
