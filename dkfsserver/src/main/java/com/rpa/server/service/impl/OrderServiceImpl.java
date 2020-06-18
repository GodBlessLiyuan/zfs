package com.rpa.server.service.impl;

import com.rpa.common.bo.BatchInfoBO;
import com.rpa.common.bo.BuyGiftBO;
import com.rpa.common.bo.OrderBO;
import com.rpa.common.mapper.*;
import com.rpa.common.pojo.BuyGiftPO;
import com.rpa.common.pojo.GodinsecUserPO;
import com.rpa.common.pojo.NewUserRecordPO;
import com.rpa.common.pojo.UserActivityPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.OrderDTO;
import com.rpa.server.service.IOrderService;
import com.rpa.server.vo.OrderVO;
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
        List<NewUserRecordPO> newUserRecordPOs = newUserRecordMapper.queryByUserId2(dto.getUd());
        for (NewUserRecordPO bo : newUserRecordPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(3);
            vo.setComname("新用户赠送");
            vo.setPaytime(bo.getCreateTime());
            orderVOs.add(vo);
        }
        // V商神器赠送
        List<GodinsecUserPO> godinsecUserPOs = godinsecUserMapper.queryByUserId2(dto.getUd());
        for (GodinsecUserPO po : godinsecUserPOs) {
            OrderVO vo = new OrderVO();
            vo.setType(4);
            vo.setComname(po.getName());
            vo.setPaytime(po.getCreateTime());
            orderVOs.add(vo);
        }

        // 卡密激活
        List<BatchInfoBO> batchInfoBOs = batchInfoMapper.queryByUserId2(dto.getUd());
        for (BatchInfoBO bo : batchInfoBOs) {
            OrderVO vo = new OrderVO();
            vo.setType(5);
            if(bo.getBatchId()==1){
                vo.setComname(comTypeMapper.queryNameDays(bo.getDays()));
            }else{
                vo.setComname(bo.getComTypeName());
            }
            vo.setOrdernumber(bo.getVipkey());
            vo.setPaytime(bo.getUpdateTime());
            orderVOs.add(vo);
        }
        //助手购买赠送
        List<BuyGiftBO> buyGiftBOS=giftMapper.queryByUserId(dto.getUd());
        for(BuyGiftBO bo:buyGiftBOS){
            OrderVO vo = new OrderVO();
            vo.setType(6);//类型：写死
            vo.setPaytype(bo.getType());//付款类型
            //标题由商品名称改为智能助手赠送（X天会员）
            vo.setComname("智能助手赠送（"+bo.getDays()+"天会员）");
            vo.setPaytime(bo.getCreateTime());
            orderVOs.add(vo);
        }
        // 根据购买时间降序排序
        Collections.sort(orderVOs);

        return new ResultVO<>(1000, orderVOs);
    }
}
