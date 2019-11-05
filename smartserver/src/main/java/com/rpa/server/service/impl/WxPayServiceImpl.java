package com.rpa.server.service.impl;

import com.rpa.server.mapper.OrderFeedbackMapper;
import com.rpa.server.pojo.OrderFeedbackPO;
import com.rpa.server.service.IWxPayService;
import com.rpa.server.utils.WxPayUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AmqpTemplate template;

    @Override
    public String wxPayNotice(HttpServletRequest req) {
        Map<String, String> info = null;

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

        OrderFeedbackPO po = this.convertMap2PO(info);

        orderFeedbackMapper.insert(po);

        this.template.convertAndSend("wx-pay-notice", po.getNonceStr());

        return WxPayUtil.successWxPay();
    }

    /**
     * Map数据转PO
     *
     * @return
     */
    private OrderFeedbackPO convertMap2PO(Map<String, String> map) {
        OrderFeedbackPO po = new OrderFeedbackPO();

        String returnCode = map.get("return_code");
        po.setReturnCode(returnCode);
        po.setReturnMsg(map.get("return_msg"));
        if ("SUCCESS".equals(returnCode)) {
            po.setAppid(map.get("appid"));
            po.setMchId(map.get("mch_id"));
            po.setDeviceInfo(map.get("device_info"));
            po.setNonceStr(map.get("nonce_str"));
            po.setSigin(map.get("sigin"));
            po.setResultCode(map.get("return_code"));
            po.setErrCode(map.get("err_code"));
            po.setErrCodeDes(map.get("err_code_des"));
            po.setOpenid(map.get("openid"));
            po.setIsSubscribe(map.get("is_subscribe"));
            po.setTradeType(map.get("trade_type"));
            po.setBankType(map.get("bank_type"));
            if (null != map.get("total_fee")) {
                po.setTotalFee(Integer.parseInt(map.get("total_fee")));
            }
            po.setFeeType(map.get("fee_type"));
            if (null != map.get("cash_fee")) {
                po.setCashFee(Integer.parseInt(map.get("cash_fee")));
            }
            po.setCashFeeType(map.get("cash_fee_type"));
            if (null != map.get("coupon_fee")) {
                po.setCouponFee(Integer.parseInt(map.get("coupon_fee")));
            }
            if (null != map.get("coupon_count")) {
                po.setCouponCount(Integer.parseInt(map.get("coupon_count")));
            }
            po.setTransactionId(map.get("transaction_id"));
            po.setOutTradeNo(map.get("out_trade_no"));
            po.setAttach(map.get("attch"));
            po.setTimeEnd(map.get("time_end"));
        }

        return po;
    }
}
