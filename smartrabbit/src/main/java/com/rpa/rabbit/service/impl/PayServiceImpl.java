package com.rpa.rabbit.service.impl;

import com.rpa.rabbit.bo.InviteUserBO;
import com.rpa.rabbit.bo.OrderBO;
import com.rpa.rabbit.constant.InviteDetailConstant;
import com.rpa.rabbit.mapper.InviteDetailMapper;
import com.rpa.rabbit.mapper.InviteUserMapper;
import com.rpa.rabbit.mapper.OrderMapper;
import com.rpa.rabbit.mapper.RevenueUserMapper;
import com.rpa.rabbit.pojo.InviteDetailPO;
import com.rpa.rabbit.pojo.RevenueUserPO;
import com.rpa.rabbit.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 18:23
 * @description: 支付服务
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class PayServiceImpl implements IPayService {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payNotify(String orderNumber) {
        // 调用方法，更新redis信息
        this.redisForRevenue();
        // 订单信息
        OrderBO orderBO = orderMapper.queryByOrderNumber(orderNumber);
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
        revenueUserPO.setRegisterCount(inviteDetailMapper.queryCountByUserId(inviteUserBO.getUserId()));
        revenueUserMapper.updateByPrimaryKey(revenueUserPO);
    }


    /**
     * @author: dangyi
     * @date: 2019.11.15
     * @description: 这是支付成功的通知消息，籍此，查询收入，更新Redis
     */
    private void redisForRevenue() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Float dayRevenue = this.orderMapper.queryDayRevenue();
        int payCount = this.orderMapper.queryPayCount();
        Float monthRevenue = this.orderMapper.queryMonthRevenue();

        List<String> revenue = new ArrayList<>();
        revenue.add(String.valueOf(dayRevenue));
        revenue.add(String.valueOf(payCount));
        revenue.add(String.valueOf(monthRevenue));
        this.template.opsForList().rightPushAll("revenue" + current_date, revenue);
        this.template.expire("revenue" + current_date, 25, TimeUnit.HOURS);
    }
}
