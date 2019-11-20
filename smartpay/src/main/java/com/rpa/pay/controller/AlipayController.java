package com.rpa.pay.controller;

import com.rpa.pay.common.ResultVO;
import com.rpa.pay.dto.AlipayDTO;
import com.rpa.pay.service.AlipayService;
import com.rpa.pay.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 17:34 2019/11/6
 * @version: 1.0.0
 * @description: 支付宝下单
 */
@RequestMapping("v1.0")
@RestController
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    /**
     * 客户端携带商品ID访问服务端，生成订单信息，并加签返回给客户端
     * @param dto
     * @param req
     * @return
     */
    @PostMapping("alipayorder")
    public ResultVO alipayOrder(@RequestBody AlipayDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return this.alipayService.alipayOrder(dto, req);
    }


    /**
     * 获取支付宝服务器发送来的支付完成通知（异步）
     * @return
     */
    @PostMapping("alipaynotify")
    public String alipayNotify(HttpServletRequest request) {

        // 存放转化后的参数集合
        Map<String,String> params = new HashMap<String,String>();

        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //验签，并且如若支付成功，更新相关信息
        String status= this.alipayService.alipayNotify(params);
        return status;
    }


    /**
     * 返回订单状态：是否完成
     * @param dto
     * @param req
     * @return
     */
    @PostMapping("paystatus")
    public ResultVO paystatus(@RequestBody AlipayDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return this.alipayService.paystatus(dto);
    }
}
