package com.rpa.pay.service.impl;

import com.rpa.pay.mapper.OrderMapper;
import com.rpa.pay.mapper.UserVipMapper;
import com.rpa.pay.mapper.WxFeedbackMapper;
import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.UserVipPO;
import com.rpa.pay.pojo.WxFeedbackPO;
import com.rpa.pay.service.IWxPayService;
import com.rpa.pay.utils.UserVipUtil;
import com.rpa.pay.utils.WxPayUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 9:54
 * @description: 微信支付
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class WxPayServiceImpl implements IWxPayService {
    @Resource
    private WxFeedbackMapper wxFeedbackMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserVipMapper userVipMapper;

    @Autowired
    private AmqpTemplate template;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String wxPayNotify(HttpServletRequest req) {
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

        OrderPO orderPO = orderMapper.queryByOrderNumber(info.get("nonce_str"));
        if (null != orderPO.getPayTime()) {
            // 当前订单已完成
            return WxPayUtil.successWxPay();
        }
        orderPO.setPayTime(new Date());
        orderMapper.updateByPrimaryKey(orderPO);

        // 更新用户会员时间
        UserVipPO userVipPO = userVipMapper.queryByUserId(orderPO.getUserId());
        UserVipPO newUserVipVO = UserVipUtil.buildUserVipVO(userVipPO, orderPO.getUserId(), orderPO.getDays(), true);
        if (null == userVipPO) {
            userVipMapper.insert(newUserVipVO);
        } else {
            userVipMapper.updateByPrimaryKey(newUserVipVO);
        }

        // 新增微信支付反馈信息
        WxFeedbackPO po = this.convertMap2PO(info);
        wxFeedbackMapper.insert(po);

        // RabbitMQ
        this.template.convertAndSend("wx-pay-notice", po.getNonceStr());

        return WxPayUtil.successWxPay();
    }

    /**
     * Map数据转PO
     *
     * @return
     */
    private WxFeedbackPO convertMap2PO(Map<String, String> map) {
        WxFeedbackPO po = new WxFeedbackPO();

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
