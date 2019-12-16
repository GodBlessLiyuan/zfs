package com.rpa.server.service.impl;

import com.rpa.server.bo.BatchInfoBO;
import com.rpa.server.bo.OrderBO;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OrderDTO;
import com.rpa.server.mapper.*;
import com.rpa.server.pojo.GodinsecUserPO;
import com.rpa.server.pojo.NewUserRecordPO;
import com.rpa.server.pojo.UserActivityPO;
import com.rpa.server.service.IOrderService;
import com.rpa.server.vo.OrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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
    private GodinsecUserMapper godinsecUserMapper;
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
            if(null != bo.getPay()) {
                vo.setPrice(String.valueOf(bo.getPay() / 100f));
            }
            orderVOs.add(vo);
        }
        // 好评活动赠送
        List<UserActivityPO> userActivityPOs = userActivityMapper.queryActivatedByUserId(dto.getUd());
        for (UserActivityPO po : userActivityPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(2);
            vo.setComname("好评活动赠送");
            vo.setPaytime(po.getUpdateTime());
            orderVOs.add(vo);
        }
        // 新用户赠送
        List<NewUserRecordPO> newUserRecordPOs = newUserRecordMapper.queryByUserId(dto.getUd());
        for (NewUserRecordPO bo : newUserRecordPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(3);
            vo.setComname("新用户赠送");
            vo.setPaytime(bo.getCreateTime());
            orderVOs.add(vo);
        }
        // V商神器赠送
        List<GodinsecUserPO> godinsecUserPOs = godinsecUserMapper.queryByUserId(dto.getUd());
        for (GodinsecUserPO po : godinsecUserPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(4);
            vo.setComname(po.getName());
            vo.setPaytime(po.getCreateTime());
            orderVOs.add(vo);
        }

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

        // 根据购买时间降序排序
        Collections.sort(orderVOs);

        return new ResultVO<>(1000, orderVOs);
    }
}
