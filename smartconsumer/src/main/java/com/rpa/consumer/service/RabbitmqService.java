package com.rpa.consumer.service;

import com.rpa.consumer.bo.InviteUserBO;
import com.rpa.consumer.bo.OrderBO;
import com.rpa.consumer.constant.InviteDetailConstant;
import com.rpa.consumer.mapper.InviteUserMapper;
import com.rpa.consumer.mapper.OrderMapper;
import com.rpa.consumer.pojo.InviteDetailPO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 10:53
 * @description: Rabbitmq 消费服务
 * @version: 1.0
 */
@Service
public class RabbitmqService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private InviteUserMapper inviteUserMapper;

    /**
     * 微信支付确认通知
     *
     * @param orderNumber 订单号
     */
    @RabbitListener(queues = "pay-notify")
    public void wxPayNotice(String orderNumber) {
        // 订单信息
        OrderBO orderBO = orderMapper.queryByOrderNumber(orderNumber);
        // 用户邀请人详情信息
        InviteUserBO inviteUserBO = inviteUserMapper.queryByInviteeId(orderBO.getUserId());
        if (null == inviteUserBO) {
            // 用户未被邀请
            return;
        }

        InviteDetailPO inviteDetailPO = new InviteDetailPO();
        inviteDetailPO.setOrderId(orderBO.getOrderId());
        inviteDetailPO.setComTypeId(orderBO.getComTypeId());
        inviteDetailPO.setComTypeName(orderBO.getComTypeName());
        inviteDetailPO.setPay(orderBO.getPay());
        inviteDetailPO.setPayTime(orderBO.getPayTime());

        int vipType = inviteUserBO.getVipTypeId();
        byte proportion = InviteDetailConstant.NONE_VIP_RATE;
        if (InviteDetailConstant.COMM_VIP == vipType) {
            proportion = InviteDetailConstant.COMM_VIP_RATE;
        } else if (InviteDetailConstant.YEAR_VIP == vipType) {
            proportion = InviteDetailConstant.YEAR_VIP_RATE;
        }
        long earnings = orderBO.getPay() * proportion / 100;

        inviteDetailPO.setViptypeId(vipType);
    }
}
