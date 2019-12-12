package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.BatchInfoBO;
import com.rpa.common.bo.NewUserRecordBO;
import com.rpa.common.bo.OrderBO;
import com.rpa.common.bo.UserActivityBO;
import com.rpa.common.bo.UserVipBO;
import com.rpa.common.mapper.*;
import com.rpa.common.pojo.GodinsecUserPO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.common.UserVipConstant;
import com.rpa.web.vo.UserVipVO;
import com.rpa.web.vo.UserVipDetailsVO;
import com.rpa.web.service.IUserVipService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:29
 * @description: 用户会员数据
 * @version: 1.0
 */
@Service
public class UserVipServiceImpl implements IUserVipService {

    @Resource
    private UserVipMapper userVipMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private NewUserRecordMapper newUserRecordMapper;

    @Resource
    private BatchInfoMapper infoMapper;

    @Resource
    private GodinsecUserMapper godinsecUserMapper;

    @Override
    public DTPageInfo<UserVipVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserVipBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserVipBO> dos = userVipMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserVipVO.convert(dos));
    }

    @Override
    public DTPageInfo<UserVipDetailsVO> queryDetails(int draw, int pageNum, int pageSize, int userId) {
        List<UserVipDetailsVO> detailsVOs = new ArrayList<>();
        // 购买
        List<OrderBO> orderBOs = orderMapper.queryByUserId(userId);
        for (OrderBO bo : orderBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_ORDER);
            dto.setUserChanName(bo.getUserChanName());
            dto.setSaleChanName(bo.getSaleChanName());
            dto.setType(bo.getType());
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }
        // 好评活动赠送
        List<UserActivityBO> userActivityBOs = userActivityMapper.queryByUserId(userId);
        for (UserActivityBO bo : userActivityBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_ACTIVITY);
            dto.setUserChanName(bo.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }
        // 新用户赠送
        List<NewUserRecordBO> newUserRecordBOs = newUserRecordMapper.queryByUserId(userId);
        for (NewUserRecordBO bo : newUserRecordBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_GIFTS);
            dto.setUserChanName(bo.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }
        // V商神器赠送
        List<GodinsecUserPO> godinsecUserPOs = godinsecUserMapper.queryByUserId(userId);
        for (GodinsecUserPO godinsecUserPO : godinsecUserPOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_V);
            dto.setUserChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(godinsecUserPO.getUpdateTime());
            dto.setComTypeName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setDays(godinsecUserPO.getDays());
            detailsVOs.add(dto);
        }

        // 卡密激活
        List<BatchInfoBO> batchInfoBOS = infoMapper.queryByUserId(userId);
        for (BatchInfoBO batchInfoBO : batchInfoBOS) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_BATCH);
            dto.setUserChanName(batchInfoBO.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(batchInfoBO.getUpdateTime());
            dto.setComTypeName(batchInfoBO.getComTypeName());
            dto.setDays(batchInfoBO.getDays());
            detailsVOs.add(dto);
        }

        // 根据购买时间降序排序
        Collections.sort(detailsVOs);

        return new DTPageInfo<>(draw, detailsVOs.size(), detailsVOs);
    }
}
