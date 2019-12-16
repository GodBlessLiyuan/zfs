package com.rpa.rabbit.service.impl;

import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.rabbit.bo.InviteUserBO;
import com.rpa.rabbit.bo.OrderBO;
import com.rpa.rabbit.constant.InviteDetailConstant;
import com.rpa.rabbit.mapper.*;
import com.rpa.rabbit.pojo.InviteDetailPO;
import com.rpa.rabbit.pojo.InviteUserPO;
import com.rpa.rabbit.pojo.OrderPO;
import com.rpa.rabbit.pojo.RevenueUserPO;
import com.rpa.rabbit.service.IIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 18:23
 * @description: 爱收益
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class IncomeServiceImpl implements IIncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeServiceImpl.class);
    private static int startOrderId = 0;

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private RevenueUserMapper revenueUserMapper;
    @Resource
    private InviteUserMapper inviteUserMapper;
    @Resource
    private InviteDetailMapper inviteDetailMapper;
    @Autowired
    private StringRedisTemplate template;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payNotify(String orderNumber) {
        // 调用方法，更新redis信息
        this.redisForRevenue();
        // 订单信息
        OrderBO orderBO = orderMapper.queryByOrderNumber(orderNumber);
        orderBO.setRevenue((byte) 2);
        orderMapper.updateByPrimaryKeySelective(orderBO);
        // 用户邀请人详情信息
        InviteUserBO inviteUserBO = inviteUserMapper.queryByInviteeId(orderBO.getUserId());
        if (null == inviteUserBO) {
            // 用户未被邀请
            return;
        }

        int vipType = inviteUserBO.getVipTypeId();
        byte proportion = InviteDetailConstant.NONE_VIP_RATE;
        if (InviteDetailConstant.COMM_VIP == vipType) {
            proportion = InviteDetailConstant.COMM_VIP_RATE;
        } else if (InviteDetailConstant.YEAR_VIP == vipType) {
            proportion = InviteDetailConstant.YEAR_VIP_RATE;
        }
        long earnings = orderBO.getPay() * proportion / 100;

        if (earnings == 0) {
            // 无收益
            return;
        }

        // 新增被邀请人详细分成
        InviteDetailPO inviteDetailPO = new InviteDetailPO();
        inviteDetailPO.setOrderId(orderBO.getOrderId());
        inviteDetailPO.setComTypeId(orderBO.getComTypeId());
        inviteDetailPO.setComTypeName(orderBO.getComTypeName());
        inviteDetailPO.setPay(orderBO.getPay());
        inviteDetailPO.setEarnings(earnings);
        inviteDetailPO.setProportion(proportion);
        inviteDetailPO.setUserId(inviteUserBO.getUserId());
        inviteDetailPO.setInviteeId(inviteUserBO.getInviteeId());
        inviteDetailPO.setViptypeId(vipType);
        inviteDetailPO.setPayTime(orderBO.getPayTime());
        inviteDetailPO.setInviteId(inviteUserBO.getInviteId());
        inviteDetailMapper.insert(inviteDetailPO);

        // 更新用户邀请收入
        RevenueUserPO revenueUserPO = revenueUserMapper.selectByPrimaryKey(inviteUserBO.getUserId());
        revenueUserPO.setTotalRevenue(revenueUserPO.getTotalRevenue() + earnings);
        revenueUserPO.setRemaining(revenueUserPO.getRemaining() + earnings);
        revenueUserPO.setPayCount(inviteDetailMapper.queryCountByUserId(inviteUserBO.getUserId()));
        revenueUserMapper.updateByPrimaryKey(revenueUserPO);
    }

    @Override
    public void register(String phone) {
        //调用方法，更新Redis
        this.redisForUser();

        List<InviteUserPO> inviteUserPOs = inviteUserMapper.queryByPhone(phone);
        if (null == inviteUserPOs || inviteUserPOs.size() != 1) {
            return;
        }

        RevenueUserPO revenueUserPO = revenueUserMapper.selectByPrimaryKey(inviteUserPOs.get(0).getUserId());
        if (null == revenueUserPO) {
            return;
        }

        // 注册数 +1
        revenueUserPO.setRegisterCount(revenueUserPO.getRegisterCount() + 1);
        revenueUserMapper.updateByPrimaryKey(revenueUserPO);
    }

    @Scheduled(fixedDelay = 2*60*60*1000)
    public void scheduled() {
        List<OrderPO> orderPOs = orderMapper.queryNonRevenue(startOrderId);
        Integer maxOrderId = orderMapper.queryMaxOrderId(startOrderId);
        if (null != maxOrderId) {
            startOrderId = maxOrderId;
        }
        if (null == orderPOs || orderPOs.size() == 0) {
            return;
        }

        for (OrderPO orderPO : orderPOs) {
            InviteDetailPO inviteDetailPO = inviteDetailMapper.queryByOrderId(orderPO.getOrderId());
            if (null == inviteDetailPO) {
                this.payNotify(orderPO.getOrderNumber());
            } else {
                orderPO.setRevenue((byte) 2);
                orderMapper.updateByPrimaryKey(orderPO);
            }
        }
    }

    /**
     * @author: dangyi
     * @date: 2019.11.15
     * @description: 这是支付成功的通知消息，籍此，查询收入，更新Redis
     */
    private void redisForRevenue() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Long dayRevenue = this.orderMapper.queryDayRevenue();
        int payCount = this.orderMapper.queryPayCount();
        Long monthRevenue = this.orderMapper.queryMonthRevenue();

        // 将统计结果封装成map
        Map<String, String> revenue = new HashMap<>(3);
        revenue.put("dayRevenue", String.valueOf(decimal((null == dayRevenue ? 0 : dayRevenue) * 0.01)));
        revenue.put("payCount", String.valueOf(payCount));
        revenue.put("monthRevenue", String.valueOf(decimal((null == monthRevenue ? 0 : monthRevenue) * 0.01)));
        String key = RedisKeyUtil.genHomepageRedisKey( "revenue", current_date);
        this.template.opsForHash().putAll(key, revenue);
        this.template.expire(key, 25, TimeUnit.HOURS);
    }


    /**
     * @author: dangyi
     * @date: 2019.12.16
     * @description: 新用户访问、注册，更新Redis
     */
    private void redisForUser() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        int newUser = this.deviceMapper.queryTodayNewUser();
        int newRegister = this.userMapper.queryTodayNewRegister();

        //将统计结果封装成map
        Map<String, String> user = new HashMap<>(2);
        user.put("newUser", String.valueOf(newUser));
        user.put("newRegister", String.valueOf(newRegister));

        //存入Redis
        String key = RedisKeyUtil.genHomepageRedisKey("user", current_date);
        this.template.opsForHash().putAll(key, user);
        this.template.expire(key, 25, TimeUnit.HOURS);
    }

    /**
     * 保留两位小数
     * @param d
     * @return
     */
    private Double decimal(Double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
