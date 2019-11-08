package com.rpa.pay.service;

import com.rpa.pay.common.ResultVO;
import com.rpa.pay.dto.AlipayDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 16:46 2019/11/7
 * @version: 1.0.0
 * @description: 支付宝下单
 */
public interface AlipayService {
    ResultVO alipayOrder(AlipayDTO dto, HttpServletRequest req);

    ResultVO paystatus(AlipayDTO dto);

    String aliNotify(Map<String, String> conversionParams);
}
