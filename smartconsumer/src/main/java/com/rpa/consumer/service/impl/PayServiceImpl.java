package com.rpa.consumer.service.impl;

import com.rpa.consumer.bo.InviteUserBO;
import com.rpa.consumer.bo.OrderBO;
import com.rpa.consumer.constant.InviteDetailConstant;
import com.rpa.consumer.mapper.InviteDetailMapper;
import com.rpa.consumer.mapper.InviteUserMapper;
import com.rpa.consumer.mapper.OrderMapper;
import com.rpa.consumer.mapper.RevenueUserMapper;
import com.rpa.consumer.pojo.InviteDetailPO;
import com.rpa.consumer.pojo.RevenueUserPO;
import com.rpa.consumer.service.IPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payNotify(String orderNumber) {
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
}
