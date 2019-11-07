package com.rpa.pay.service.impl;

import com.rpa.pay.common.ResultVO;
import com.rpa.pay.config.WxPayConfig;
import com.rpa.pay.constant.WxPayConstant;
import com.rpa.pay.dto.WxPayDTO;
import com.rpa.pay.mapper.OrderMapper;
import com.rpa.pay.mapper.UserVipMapper;
import com.rpa.pay.mapper.VipCommodityMapper;
import com.rpa.pay.mapper.WxFeedbackMapper;
import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.UserVipPO;
import com.rpa.pay.pojo.VipCommodityPO;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
    private VipCommodityMapper vipCommodityMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private WxFeedbackMapper wxFeedbackMapper;

    @Autowired
    private AmqpTemplate template;
    @Autowired
    private WxPayConfig wxPayConfig;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO wxPayOrder(WxPayDTO dto, HttpServletRequest req) {
        // 获取商品
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(dto.getCmdyid());
        if (null == vipCommodityPO) {
            return new ResultVO(2000);
        }

        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DATE, vipCommodityPO.getDays());
        Date endDate = calendar.getTime();

        // 创建订单
        OrderPO orderPO = new OrderPO();
        orderPO.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
        orderPO.setUserDeviceId(dto.getUdd());
        orderPO.setCmdyId(dto.getCmdyid());
        orderPO.setUserId(dto.getUd());
        orderPO.setDeviceId(dto.getId());
        orderPO.setCreateTime(curDate);
        orderPO.setStarttime(curDate);
        orderPO.setEndtime(endDate);
        orderPO.setType(1);
        orderPO.setDays(vipCommodityPO.getDays());
        orderPO.setPay(vipCommodityPO.getDiscount());
        orderMapper.insert(orderPO);

        // 微信支付请求参数
        String wxPayParam = WxPayUtil.createReqParam(orderPO, wxPayConfig, req);

        // 调用微信支付下单请求
        String wxPayRes = WxPayUtil.httpsRequest(wxPayConfig.getOrder_url(), wxPayParam);

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String wxPayNotify(HttpServletRequest req) {
        Map<String, String> info = WxPayUtil.parseReq(req);
        if (info == null || info.size() == 0) {
            return WxPayUtil.failWxPay();
        }

        OrderPO orderPO = orderMapper.queryByOrderNumber(info.get(WxPayConstant.OUT_TRADE_NO));
        if (null == orderPO) {
            return WxPayUtil.failWxPay();
        }
        if (null != orderPO.getPayTime()) {
            // 当前订单已完成
            return WxPayUtil.successWxPay();
        }

        // 更新支付时间
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
        WxFeedbackPO po = WxPayUtil.convertMap2PO(info);
        wxFeedbackMapper.insert(po);

        // RabbitMQ
        this.template.convertAndSend("wx-pay-notice", po.getOutTradeNo());

        return WxPayUtil.successWxPay();
    }
}
