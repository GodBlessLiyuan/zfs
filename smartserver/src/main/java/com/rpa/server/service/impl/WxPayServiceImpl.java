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

        OrderFeedbackPO po = new OrderFeedbackPO();
        String returnCode = info.get("return_code");
        po.setReturnCode(returnCode);
        po.setReturnMsg(info.get("return_msg"));
        if ("SUCCESS".equals(returnCode)) {
            po.setAppid(info.get("appid"));
            po.setMchId(info.get("mch_id"));
            po.setDeviceInfo(info.get("device_info"));
            po.setNonceStr(info.get("nonce_str"));
            po.setSigin(info.get("sigin"));
            po.setResultCode(info.get("return_code"));
            po.setErrCode(info.get("err_code"));
            po.setErrCodeDes(info.get("err_code_des"));
            po.setOpenid(info.get("openid"));
            po.setIsSubscribe(info.get("is_subscribe"));
            po.setTradeType(info.get("trade_type"));
            po.setBankType(info.get("bank_type"));
            if (null != info.get("total_fee")) {
                po.setTotalFee(Integer.parseInt(info.get("total_fee")));
            }
            po.setFeeType(info.get("fee_type"));
            if (null != info.get("cash_fee")) {
                po.setCashFee(Integer.parseInt(info.get("cash_fee")));
            }
            po.setCashFeeType(info.get("cash_fee_type"));
            if (null != info.get("coupon_fee")) {
                po.setCouponFee(Integer.parseInt(info.get("coupon_fee")));
            }
            if (null != info.get("coupon_count")) {
                po.setCouponCount(Integer.parseInt(info.get("coupon_count")));
            }
            po.setTransactionId(info.get("transaction_id"));
            po.setOutTradeNo(info.get("out_trade_no"));
            po.setAttach(info.get("attch"));
            po.setTimeEnd(info.get("time_end"));
        }

        orderFeedbackMapper.insert(po);

        return WxPayUtil.successWxPay();
    }
}
