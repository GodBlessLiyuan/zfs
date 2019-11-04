package com.rpa.server.service.impl;

import com.rpa.server.mapper.OrderFeedbackMapper;
import com.rpa.server.pojo.OrderFeedbackPO;
import com.rpa.server.service.IWxPayService;
import com.rpa.server.utils.WxPayUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 9:54
 * @description: 微信支付
 * @version: 1.0
 */
@Service
public class WxPayServiceImpl implements IWxPayService {
    @Resource
    private OrderFeedbackMapper orderFeedbackMapper;

    @Override
    public String wxPayNotice(HttpServletRequest req) {
        Map<String, Object> info = null;
        try {
            InputStream is = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            reader.close();
            is.close();

            info = WxPayUtil.parseXML(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (info == null || info.size() == 0) {
            return WxPayUtil.failWxPay();
        }

        OrderFeedbackPO po = new OrderFeedbackPO();
        po.setAppid((String) info.get("appid"));
        po.setMchId((String) info.get("mch_id"));
        po.setNonceStr((String) info.get("nonce_str"));
        po.setSigin((String) info.get("info"));
        po.setResultCode((String) info.get("result_code"));
        po.setErrCode((String) info.get("err_code"));
        po.setErrCodeDes((String) info.get("err_code_des"));
        po.setOpenid((String) info.get("openid"));
        po.setIsSubscribe((String) info.get("is_subscribe"));
        po.setTradeType((String) info.get("trade_type"));
        po.setBankType((String) info.get("bank_type"));
        po.setTotalFee((Integer) info.get("total_fee"));
        po.setFeeType((String) info.get("fee_type"));


        orderFeedbackMapper.insert(po);

        return WxPayUtil.successWxPay();
    }
}
