package com.rpa.server.service.impl;

import com.rpa.server.bo.BatchInfoBO;
import com.rpa.server.bo.NewUserRecordBO;
import com.rpa.server.bo.OrderBO;
import com.rpa.server.bo.UserActivityBO;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OrderDTO;
import com.rpa.server.mapper.BatchInfoMapper;
import com.rpa.server.mapper.NewUserRecordMapper;
import com.rpa.server.mapper.OrderMapper;
import com.rpa.server.mapper.UserActivityMapper;
import com.rpa.server.service.IOrderService;
import com.rpa.server.vo.OrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 16:46
 * @description: 订单
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserActivityMapper userActivityMapper;
    @Resource
    private NewUserRecordMapper newUserRecordMapper;
    @Resource
    private BatchInfoMapper batchInfoMapper;

    @Override
    public ResultVO getOrders(OrderDTO dto) {
        List<OrderVO> orderVOs = new ArrayList<>();
        // 购买
        List<OrderBO> orderBOs = orderMapper.queryByUserId(dto.getUd());
        for (OrderBO bo : orderBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(1);
            vo.setPaytype(bo.getType());
            vo.setComname(bo.getComName());
            vo.setOrdernumber(bo.getOrderNumber());
            vo.setPaytime(bo.getPayTime());
            vo.setPrice(bo.getPrice());
            orderVOs.add(vo);
        }
        // 好评活动赠送
        List<UserActivityBO> userActivityBOs = userActivityMapper.queryActivatedByUserId(dto.getUd());
        for (UserActivityBO bo : userActivityBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(2);
            vo.setComname(bo.getComTypeName());
            vo.setPaytime(bo.getUpdateTime());
            orderVOs.add(vo);
        }
        // 新用户赠送
        List<NewUserRecordBO> newUserRecordBOs = newUserRecordMapper.queryByUserId(dto.getUd());
        for (NewUserRecordBO bo : newUserRecordBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(3);
            vo.setComname(bo.getComTypeName());
            vo.setPaytime(bo.getCreateTime());
            orderVOs.add(vo);
        }
        // V商神器赠送
        // 卡密激活
        List<BatchInfoBO> batchInfoBOs = batchInfoMapper.queryByUserId(dto.getUd());
        for (BatchInfoBO bo : batchInfoBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(5);
            vo.setComname(bo.getComTypeName());
            vo.setOrdernumber(bo.getVipkey());
            vo.setPaytime(bo.getUpdateTime());
            orderVOs.add(vo);
        }

        return new ResultVO<>(1000, orderVOs);
    }
}
