package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.bo.NewUserRecordBO;
import com.rpa.common.bo.OrderBO;
import com.rpa.common.mapper.NewUserRecordMapper;
import com.rpa.common.mapper.OrderMapper;
import com.rpa.web.common.PageHelper;
import com.rpa.web.common.UserVipConstant;
import com.rpa.web.domain.*;
import com.rpa.web.dto.UserVipDTO;
import com.rpa.web.dto.UserVipDetailsDTO;
import com.rpa.web.mapper.*;
import com.rpa.web.pojo.GodinsecUserPO;
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
    public DTPageInfo<UserVipDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserVipDO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserVipDO> dos = userVipMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), UserVipDTO.convert(dos));
    }

    @Override
    public DTPageInfo<UserVipDetailsDTO> queryDetails(int draw, int pageNum, int pageSize, int userId) {
        List<UserVipDetailsDTO> userVipDetailsDTOs = new ArrayList<>();
        // 购买
        List<OrderBO> orderBOs = orderMapper.queryByUserId(userId);
        for (OrderBO bo : orderBOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipConstant.USER_VIP_ORDER);
            dto.setUserChanName(bo.getUserChanName());
            dto.setSaleChanName(bo.getSaleChanName());
            dto.setType(bo.getType());
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // 好评活动赠送
        List<UserActivityDO> userActivityDOs = userActivityMapper.queryByUserId(userId);
        for (UserActivityDO userActivityDO : userActivityDOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipConstant.USER_VIP_ACTIVITY);
            dto.setUserChanName(userActivityDO.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(userActivityDO.getCreateTime());
            dto.setComTypeName(userActivityDO.getComTypeName());
            dto.setDays(userActivityDO.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // 新用户赠送
        List<NewUserRecordBO> newUserRecordBOs = newUserRecordMapper.queryByUserId(userId);
        for (NewUserRecordBO bo : newUserRecordBOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipConstant.USER_VIP_GIFTS);
            dto.setUserChanName(bo.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // V商神器赠送
        List<GodinsecUserPO> godinsecUserPOs = godinsecUserMapper.queryByUserId(userId);
        for (GodinsecUserPO godinsecUserPO : godinsecUserPOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipConstant.USER_VIP_V);
            dto.setUserChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(godinsecUserPO.getUpdateTime());
            dto.setComTypeName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setDays(godinsecUserPO.getDays());
            userVipDetailsDTOs.add(dto);
        }

        // 卡密激活
        List<BatchInfoDO> batchInfoDOs = infoMapper.queryByUserId(userId);
        for (BatchInfoDO batchInfoDO : batchInfoDOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipConstant.USER_VIP_BATCH);
            dto.setUserChanName(batchInfoDO.getUserChanName());
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(batchInfoDO.getUpdateTime());
            dto.setComTypeName(batchInfoDO.getComTypeName());
            dto.setDays(batchInfoDO.getDays());
            userVipDetailsDTOs.add(dto);
        }

        // 根据购买时间降序排序
        Collections.sort(userVipDetailsDTOs);

        return new DTPageInfo<>(draw, userVipDetailsDTOs.size(), userVipDetailsDTOs);
    }
}
