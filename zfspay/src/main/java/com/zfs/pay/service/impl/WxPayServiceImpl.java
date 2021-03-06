package com.zfs.pay.service.impl;

import com.zfs.common.constant.UserVipConstant;
import com.zfs.common.utils.LogUtil;

import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.pay.config.WxPayConfig;
import com.zfs.pay.constant.WxPayConstant;
import com.zfs.pay.dto.WxPayDTO;
import com.zfs.pay.mapper.OrderMapper;
import com.zfs.pay.mapper.UserVipMapper;
import com.zfs.pay.mapper.VipCommodityMapper;
import com.zfs.pay.mapper.WxFeedbackMapper;
import com.zfs.pay.pojo.OrderPO;
import com.zfs.pay.pojo.UserVipPO;
import com.zfs.pay.pojo.VipCommodityPO;
import com.zfs.pay.pojo.WxFeedbackPO;
import com.zfs.pay.service.IWxPayService;
import com.zfs.pay.utils.RedisMapUtil;
import com.zfs.pay.utils.UserVipUtil;
import com.zfs.pay.utils.WxPayUtil;
import com.zfs.pay.vo.WxPayVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
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
    private final static Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
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
    @Autowired
    private RedisMapUtil redisMapUtil;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO wxPayOrder(WxPayDTO dto, HttpServletRequest req) {
        // 获取商品
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(dto.getCmdyid());
        if (null == vipCommodityPO) {
            logger.error("没有商品号：{}",dto.getCmdyid());
            return ResultVO.serverInnerError();
        }

        // 创建订单
        OrderPO orderPO = new OrderPO();

        orderPO.setOrderNumber(WxPayUtil.genOrderNumber());
        orderPO.setUserDeviceId(dto.getUdd());
        orderPO.setCmdyId(dto.getCmdyid());
        orderPO.setUserId(dto.getUd());
        orderPO.setDeviceId(dto.getId());
        orderPO.setCreateTime(new Date());
        orderPO.setType(1);
        orderPO.setDays(vipCommodityPO.getDays());
        orderPO.setPay(vipCommodityPO.getDiscount());
        orderPO.setStatus((byte) 1);
        int result = orderMapper.insert(orderPO);
        if (result == 0) {
            LogUtil.log(logger, "wxPayOrder", "创建订单失败", orderPO);
        }

        // 微信支付请求参数
        String wxPayParam = WxPayUtil.createReqParam(orderPO, wxPayConfig, req);

        // 调用微信支付下单请求
        String wxPayRes = WxPayUtil.httpsRequest(wxPayConfig.getOrder_url(), wxPayParam);
        Map<String, String> wxPayMap = WxPayUtil.parseXML(wxPayRes);
        if (null == wxPayMap || 0 == wxPayMap.size()) {
            return ResultVO.serverInnerError();
        }

        if (!WxPayConstant.SUCCESS.equals(wxPayMap.get(WxPayConstant.RETURN_CODE))) {
            logger.warn(wxPayMap.get(WxPayConstant.RETURN_MSG));
            return ResultVO.serverInnerError();
        }

        WxPayVO vo = new WxPayVO();
        vo.setAppid(wxPayMap.get(WxPayConstant.APPID));
        vo.setNoncestr(wxPayMap.get(WxPayConstant.NONCE_STR));
        vo.setOrder_number(orderPO.getOrderNumber());
        vo.setPkg(wxPayConfig.getWx_package());
        vo.setPartnerid(wxPayMap.get(WxPayConstant.MCH_ID));
        vo.setPrepayid(wxPayMap.get(WxPayConstant.PREPAY_ID));
        vo.setPrice(orderPO.getPay());
        vo.setTimestamp(System.currentTimeMillis());
        vo.setSign(WxPayUtil.signRes(vo, wxPayConfig));

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
        logger.info("WxPayNotify: " + wxPayMap.toString());
//         签名验证
        if (!WxPayUtil.checkSign(wxPayMap, wxPayConfig.getKey())) {
            return WxPayUtil.failWxPay();
        }

        OrderPO orderPO = orderMapper.queryByOrderNumber(wxPayMap.get(WxPayConstant.OUT_TRADE_NO));
        if (null == orderPO || orderPO.getPay() != Long.parseLong(wxPayMap.get(WxPayConstant.TOTAL_FEE))) {
            return WxPayUtil.failWxPay();
        }
        if (null != orderPO.getPayTime()) {
            // 当前订单已完成
            return WxPayUtil.successWxPay();
        }

//         更新用户会员时间
        UserVipPO userVipPO = userVipMapper.queryByUserId(orderPO.getUserId());
        UserVipPO newUserVipVO = UserVipUtil.buildUserVipVO(userVipPO, orderPO.getUserId(), orderPO.getDays(), true);
        int result1;
        if (null == userVipPO) {
            result1 = userVipMapper.insert(newUserVipVO);
        } else {
            result1 = userVipMapper.updateByPrimaryKey(newUserVipVO);
        }
        if (result1 == 0) {
            LogUtil.log(logger, "wxPayNotify", "插入或更新用户会员数据失败", newUserVipVO);
        }
        //删除缓存
        String key = RedisKeyUtil.genRedisKey(UserVipConstant.UserID,userVipPO.getUserId());
        redisMapUtil.hdel(key);
        Date endDate = newUserVipVO.getEndTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, -orderPO.getDays());
        Date startDate = calendar.getTime();

        // 更新支付时间、开始时间、结束时间
        orderPO.setPayTime(new Date());
        orderPO.setStarttime(startDate);
        orderPO.setEndtime(endDate);
        orderPO.setStatus((byte) 2);
        int result2 = orderMapper.updateByPrimaryKey(orderPO);
        if (result2 == 0) {
            LogUtil.log(logger, "wxPayNotify", "更新订单数据失败", orderPO);
        }

        // 新增微信支付反馈信息
        WxFeedbackPO po = WxPayUtil.convertMap2PO(wxPayMap);
        int result3 = wxFeedbackMapper.insert(po);
        if (result3 == 0) {
            LogUtil.log(logger, "wxPayNotify", "创建订单失败", orderPO);
        }
        // 事务提交完成后，发送消息
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                      @Override
                                                                      public void afterCommit() {
                                                                          // RabbitMQ
                                                                          template.convertAndSend("pay-notify", po.getOutTradeNo());
                                                                      }
                                                                  }
        );
        return WxPayUtil.successWxPay();
    }
}
