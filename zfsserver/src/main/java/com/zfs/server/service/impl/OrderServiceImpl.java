package com.zfs.server.service.impl;

import com.zfs.common.bo.BatchInfoBO;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.mapper.*;
import com.zfs.common.pojo.NewUserRecordPO;
import com.zfs.common.pojo.UserActivityPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.OrderDTO;
import com.zfs.server.service.IOrderService;
import com.zfs.server.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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
    @Autowired
    private ComTypeMapper comTypeMapper;
    @Autowired
    private BuyGiftMapper giftMapper;

    @Override
    public ResultVO getOrders(OrderDTO dto, int version) {
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
            if (null != bo.getPay()) {
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
        List<NewUserRecordPO> newUserRecordPOs = newUserRecordMapper.queryByUserId2(dto.getUd());
        for (NewUserRecordPO bo : newUserRecordPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(3);
            vo.setComname("新用户赠送");
            vo.setPaytime(bo.getCreateTime());
            orderVOs.add(vo);
        }

        // 卡密激活
        List<BatchInfoBO> batchInfoBOs = batchInfoMapper.queryByUserId2(dto.getUd());
        for (BatchInfoBO bo : batchInfoBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(5);
            if (bo.getBatchId() == 1) {
                vo.setComname(comTypeMapper.queryNameDays(bo.getDays()));//其实只走这一句
            } else {
                vo.setComname(bo.getComTypeName());
            }
            vo.setOrdernumber(bo.getVipkey());
            vo.setPaytime(bo.getUpdateTime());
            orderVOs.add(vo);
        }
        // 根据购买时间降序排序
        Collections.sort(orderVOs);

        return new ResultVO<>(1000, orderVOs);
    }
}
