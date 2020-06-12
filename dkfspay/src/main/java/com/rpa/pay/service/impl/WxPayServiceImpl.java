package com.rpa.pay.service.impl;

import com.rpa.common.utils.LogUtil;
import com.rpa.pay.bo.UserToBO;
import com.rpa.pay.common.ResultVO;
import com.rpa.pay.config.WxPayConfig;
import com.rpa.pay.constant.WxPayConstant;
import com.rpa.pay.dto.WxPayDTO;
import com.rpa.pay.mapper.OrderMapper;
import com.rpa.pay.mapper.UserVipMapper;
import com.rpa.pay.mapper.VipCommodityMapper;
import com.rpa.pay.mapper.WxFeedbackMapper;
import com.rpa.pay.mapper.UserMapper;
import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.UserVipPO;
import com.rpa.pay.pojo.VipCommodityPO;
import com.rpa.pay.pojo.WxFeedbackPO;
import com.rpa.pay.service.IWxPayService;
import com.rpa.pay.utils.UserVipUtil;
import com.rpa.pay.utils.WxPayUtil;
import com.rpa.pay.vo.WxPayVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.client.RestTemplate;

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
    private UserMapper userMapper;
    @Value("${ZnzsUrl.dkfs_buy}")
    private String dkfs_buy;
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
            return new ResultVO(2000);
        }

        if (!WxPayConstant.SUCCESS.equals(wxPayMap.get(WxPayConstant.RETURN_CODE))) {
            logger.warn(wxPayMap.get(WxPayConstant.RETURN_MSG));
            return new ResultVO<>(2000);
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
        VipCommodityPO vipCommodityPO = vipCommodityMapper.selectByPrimaryKey(orderPO.getCmdyId());
        if(vipCommodityPO.getCommAttr()==null||vipCommodityPO.getCommAttr()==1){
            return WxPayUtil.successWxPay();
        }
        else if(vipCommodityPO.getCommAttr()==2){
            UserToBO userToBO = userMapper.selPri(orderPO.getUserId());
            userToBO.setDay(orderPO.getDays());
            userToBO.setCmdyName(vipCommodityPO.getName());//渠道名
            userToBO.setComTypeName(vipCommodityPO.getComTypeName());//商品类型名称
            userToBO.setComName(vipCommodityPO.getComName());
            userToBO.setType((byte) 1);
            userToBO.setOrderNumber(orderPO.getOrderNumber());
            try{
                RestTemplate restTemplate=new RestTemplate();
                ResultVO s = restTemplate.postForObject(dkfs_buy, userToBO, ResultVO.class);
                logger.info("测试返回对象："+s.toString());
                if(s!=null&&s.getStatus()==1000){
                    logger.info("多开分身使用微信购买商品的赠送助手业务成功");
                }
                else if(s.getStatus()==2001){
                    LogUtil.log(logger,"wxPayNotify","多开使用微信赠送给助手出现重复调用",userToBO.toString());
                }else{
                    LogUtil.log(logger,"wxPayNotify","多开分身使用微信购买商品的赠送助手返回状态码错误");
                }
            }catch (Exception e){
                e.printStackTrace();
                LogUtil.log(logger,"wxPayNotify","多开微信RestTemplate调用异常",userToBO.toString());
            }


        }
        return WxPayUtil.successWxPay();
    }
}
