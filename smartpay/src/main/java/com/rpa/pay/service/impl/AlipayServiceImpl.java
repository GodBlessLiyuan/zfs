package com.rpa.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.rpa.pay.common.ResultVO;
import com.rpa.pay.dto.AlipayDTO;
import com.rpa.pay.mapper.AliFeedbackMapper;
import com.rpa.pay.mapper.OrderMapper;
import com.rpa.pay.mapper.UserVipMapper;
import com.rpa.pay.mapper.VipCommodityMapper;
import com.rpa.pay.pojo.AliFeedbackPO;
import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.UserVipPO;
import com.rpa.pay.pojo.VipCommodityPO;
import com.rpa.pay.service.AlipayService;
import com.rpa.pay.utils.UserVipUtil;
import com.rpa.pay.utils.WxPayUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 16:48 2019/11/7
 * @version: 1.0.0
 * @description:
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private VipCommodityMapper vipCommodityMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserVipMapper userVipMapper;

    @Autowired
    private AmqpTemplate template;

    @Autowired
    private AliFeedbackMapper aliFeedbackMapper;

    @Value("${alipayconfig.appid}")
    private String APP_ID;

    @Value("${alipayconfig.alipay_gateway}")
    private String ALIPAY_GATEWAY;

    @Value("${alipayconfig.app_private_key}")
    private String APP_PRIVATE_KEY;

    @Value("${alipayconfig.alipay_public_key}")
    private String ALIPAY_PUBLIC_KEY;

    @Value("${alipayconfig.alipay_notify}")
    private String ALIPAY_NOTIFY;

    /**
     * 客户端携带商品ID访问服务端，生成订单信息，并加签返回给客户端
     * @param dto
     * @param req
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO alipayOrder(AlipayDTO dto, HttpServletRequest req) {
        // 根据商品ID，获取商品详情
        VipCommodityPO vipCommodityPO = this.vipCommodityMapper.selectByPrimaryKey(dto.getCmdyid());
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
        orderPO.setType(2);
        orderPO.setDays(vipCommodityPO.getDays());
        orderPO.setPay(vipCommodityPO.getDiscount());

        this.orderMapper.insert(orderPO);


        String orderNumber = orderPO.getOrderNumber();
        Long totalAmount = vipCommodityPO.getDiscount();
        // 向支付宝服务器发起加签请求
        String orderString = this.sign(orderNumber, totalAmount);

        // 返回结果给客户端
        Map<String, Object> result = new HashMap();
        result.put("order_number", orderNumber);
        result.put("orderString", orderString);

        return new ResultVO(1000, result);
    }


    /**
     * 支付宝客户端对订单信息加签
     * @return
     */
    private String sign(String orderNumber, Long totalAmount) {
        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, APP_ID, APP_PRIVATE_KEY,
                "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");
        // 实例化具体API对应的request类
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // 传入业务参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(orderNumber);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(String.valueOf(totalAmount*0.01));
        model.setProductCode("QUICK_MSECURITY_PAY");
        // 封装参数
        request.setBizModel(model);
        // 支付结果异步通知，参数是一个供支付宝服务器可访问的地址
        request.setNotifyUrl(ALIPAY_NOTIFY);

        try {
            // 执行加签操作
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            // 返回加签后的结果
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 获取支付宝服务器发送来的支付完成通知（异步）
     * 先对通知信息进行保存，再对通知信息验签
     * 验签成功，在response中返回success，校验失败返回fail
     * 支付宝官方建议校验的值：out_trade_no、total_amount、sellerId、app_id
     * @param params
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String alipayNotify(Map<String, String> params) {
        logger.info("alipayNotify: " + params.toString());

        this.storeInfo(params);

        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        boolean flag;

        try {
            //调用SDK，对通知报文验签
            flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "utf-8", "RSA2");

            //对验签结果进行处理
            if (flag) {
                //验签通过 获取交易状态
                logger.info("支付宝回调签名验证成功");
                String tradeStatus = params.get("trade_status");
                //只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功
                if (tradeStatus.equals("TRADE_SUCCESS") ||tradeStatus.equals("TRADE_FINISHED")) {
                    //交易成功，获取商户订单号
                    String orderNumber = params.get("out_trade_no");
                    // 更新相关信息
                    return this.updateInfo(orderNumber);
                } else {
                    return "fail";
                }
            } else{
                //验签不通过
                logger.info("验签不通过！");
                return "fail";
            }
        } catch (AlipayApiException e) {
            logger.info("验签失败！");
            e.printStackTrace();
            return "fail";
        }
    }


    /**
     * 保存支付宝返回的通知信息，无论验签成功与否
     * @param params
     */
    public void storeInfo(Map<String, String> params) {

        AliFeedbackPO po = new AliFeedbackPO();

        if (params.containsKey("notify_time")) {
            po.setNotifyTime(str2date(params.get("notify_time")));
        }
        if (params.containsKey("notify_type")) {
            po.setNotifyType(params.get("notify_type"));
        }
        if (params.containsKey("notify_id")) {
            po.setNotifyId(params.get("notify_id"));
        }
        if (params.containsKey("app_time")) {
            po.setAppId(params.get("app_time"));
        }
        if (params.containsKey("version")) {
            po.setVersion(params.get("version"));
        }
        if (params.containsKey("sign_type")) {
            po.setSignType(params.get("sign_type"));
        }
        if (params.containsKey("trade_no")) {
            po.setTradeNo(params.get("trade_no"));
        }
        if (params.containsKey("out_trade_no")) {
            po.setOutTradeNo(params.get("out_trade_no"));
        }
        if (params.containsKey("out_biz_no")) {
            po.setOutBizNo(params.get("out_biz_no"));
        }
        if (params.containsKey("buyer_id")) {
            po.setBuyerId(params.get("buyer_id"));
        }
        if (params.containsKey("buyer_logon_id")) {
            po.setBuyerLogonId(params.get("buyer_logon_id"));
        }
        if (params.containsKey("seller_id")) {
            po.setSellerId(params.get("seller_id"));
        }
        if (params.containsKey("seller_email")) {
            po.setSellerEmail(params.get("seller_email"));
        }
        if (params.containsKey("trade_status")) {
            po.setTradeStatus(params.get("trade_status"));
        }
        if (params.containsKey("total_amount")) {
            po.setTotalAmount(Float.valueOf(params.get("total_amount")));
        }
        if (params.containsKey("receipt_amount")) {
            po.setReceiptAmount(Float.valueOf(params.get("receipt_amount")));
        }
        if (params.containsKey("invoice_amount")) {
            po.setInvoiceAmount(Float.valueOf(params.get("invoice_amount")));
        }
        if (params.containsKey("buyer_pay_amount")) {
            po.setBuyerPayAmount(Float.valueOf(params.get("buyer_pay_amount")));
        }
        if (params.containsKey("point_amount")) {
            po.setPointAmount(Float.valueOf(params.get("point_amount")));
        }
        if (params.containsKey("refund_fee")) {
            po.setRefundFee(Float.valueOf(params.get("refund_fee")));
        }
        if (params.containsKey("subject")) {
            po.setSubject(params.get("subject"));
        }
        if (params.containsKey("body")) {
            po.setBody(params.get("body"));
        }
        if (params.containsKey("gmt_create")) {
            po.setGmtCreate(str2date(params.get("gmt_create")));
        }
        if (params.containsKey("gmt_payment")) {
            po.setGmtPayment(str2date(params.get("gmt_payment")));
        }
        if (params.containsKey("gmt_refund")) {
            po.setGmtRefund(str2date(params.get("gmt_refund")));
        }
        if (params.containsKey("gmt_close")) {
            po.setGmtClose(str2date(params.get("gmt_close")));
        }
        if (params.containsKey("fund_bill_list")) {
            po.setFundBillList(params.get("fund_bill_list"));
        }

        this.aliFeedbackMapper.insert(po);
    }



    /**
     * 验签通过且交易成功，更新相关信息
     * @param orderNumber
     */
    public String updateInfo(String orderNumber) {

        //根据订单号，查询出订单
        OrderPO orderPO = this.orderMapper.queryByOrderNumber(orderNumber);
        if (null == orderPO) {
            logger.info("未查出订单信息");
            return "fail";
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

        // 更新订单表的支付时间、开始时间、结束时间
        orderPO.setPayTime(new Date());
        orderPO.setStarttime(startDate);
        orderPO.setEndtime(endDate);
        this.orderMapper.updateByPrimaryKey(orderPO);

        // 事务提交完成后，使用RabbitMQ，对其他模块进行异步通知
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                                                                      @Override
                                                                      public void afterCommit() {
                                                                          template.convertAndSend("pay-notify", orderNumber);
                                                                      }
                                                                  }
        );
        logger.info("更新相关信息成功");
        return "success";
    }



    /**
     * 返回订单状态：是否完成
     * @param dto
     * @return
     */
    @Override
    public ResultVO paystatus(AlipayDTO dto) {
        // 根据订单号，查询订单详情
        OrderPO orderPO = this.orderMapper.queryByOrderNumber(dto.getOrderNumber());
        if (null == orderPO) {
            return new ResultVO(1023);
        } else {
            return null == orderPO.getPayTime() ? new ResultVO(1022) : new ResultVO(1000);
        }
    }


    /**
     * 数据转换：将字符串转换为日期
     * @param str
     * @return
     */
    public Date str2date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
