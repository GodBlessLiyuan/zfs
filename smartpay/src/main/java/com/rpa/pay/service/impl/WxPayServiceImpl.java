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
import com.rpa.pay.vo.WxPayVO;
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

        // 创建订单
        OrderPO orderPO = new OrderPO();
        orderPO.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
        orderPO.setUserDeviceId(dto.getUdd());
        orderPO.setCmdyId(dto.getCmdyid());
        orderPO.setUserId(dto.getUd());
        orderPO.setDeviceId(dto.getId());
        orderPO.setCreateTime(new Date());
        orderPO.setType(1);
        orderPO.setDays(vipCommodityPO.getDays());
        orderPO.setPay(vipCommodityPO.getDiscount());
        orderMapper.insert(orderPO);

        // 微信支付请求参数
        String wxPayParam = WxPayUtil.createReqParam(orderPO, wxPayConfig, req);

        // 调用微信支付下单请求
        String wxPayRes = WxPayUtil.httpsRequest(wxPayConfig.getOrder_url(), wxPayParam);
        Map<String, String> wxPayMap = WxPayUtil.parseXML(wxPayRes);
        if (null == wxPayMap || 0 == wxPayMap.size()) {
            return new ResultVO(2000);
        }

        if (!WxPayConstant.SUCCESS.equals(wxPayMap.get(WxPayConstant.RETURN_CODE))) {
            return new ResultVO<>(2000, wxPayMap.get(WxPayConstant.RETURN_MSG));
        }

        WxPayVO vo = new WxPayVO();
        vo.setAppid(wxPayMap.get(WxPayConstant.APPID));
        vo.setNoncestr(wxPayMap.get(WxPayConstant.NONCE_STR));
        vo.setOrder_number(orderPO.getOrderNumber());
        vo.setPkg(wxPayConfig.getWx_package());
        vo.setPartnerid(wxPayMap.get(WxPayConstant.MCH_ID));
        vo.setPrepayid(wxPayMap.get(WxPayConstant.PREPAY_ID));
        vo.setPrice(orderPO.getPay());
        vo.setSign(wxPayMap.get(WxPayConstant.SIGN));
        vo.setTimestamp(System.currentTimeMillis());

        return new ResultVO<>(1000, vo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String wxPayNotify(HttpServletRequest req) {
        Map<String, String> wxPayMap = WxPayUtil.parseReq(req);
        if (null == wxPayMap || 0 == wxPayMap.size()) {
            return WxPayUtil.failWxPay();
        }
        if (!WxPayConstant.SUCCESS.equals(wxPayMap.get(WxPayConstant.RETURN_CODE))) {
            return WxPayUtil.failWxPay();
        }

        OrderPO orderPO = orderMapper.queryByOrderNumber(wxPayMap.get(WxPayConstant.OUT_TRADE_NO));
        if (null == orderPO) {
            return WxPayUtil.failWxPay();
        }
        if (null != orderPO.getPayTime()) {
            // 当前订单已完成
            return WxPayUtil.successWxPay();
        }

        // 更新用户会员时间
        UserVipPO userVipPO = userVipMapper.queryByUserId(orderPO.getUserId());
        UserVipPO newUserVipVO = UserVipUtil.buildUserVipVO(userVipPO, orderPO.getUserId(), orderPO.getDays(), true);
        if (null == userVipPO) {
            userVipMapper.insert(newUserVipVO);
        } else {
            userVipMapper.updateByPrimaryKey(newUserVipVO);
        }

        Date endDate = newUserVipVO.getEndTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, -orderPO.getDays());
        Date startDate = calendar.getTime();

        // 更新支付时间、开始时间、结束时间
        orderPO.setPayTime(new Date());
        orderPO.setStarttime(startDate);
        orderPO.setEndtime(endDate);
        orderMapper.updateByPrimaryKey(orderPO);

        // 新增微信支付反馈信息
        WxFeedbackPO po = WxPayUtil.convertMap2PO(wxPayMap);
        wxFeedbackMapper.insert(po);

        // RabbitMQ
        this.template.convertAndSend("pay-notify", po.getOutTradeNo());

        return WxPayUtil.successWxPay();
    }
}
