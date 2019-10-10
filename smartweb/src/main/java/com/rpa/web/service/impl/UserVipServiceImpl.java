package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.domain.*;
import com.rpa.web.dto.UserVipDTO;
import com.rpa.web.dto.UserVipDetailsDTO;
import com.rpa.web.mapper.*;
import com.rpa.web.service.IUserVipService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<OrderDO> orderPOs = orderMapper.queryByUserId(userId);
        for(OrderDO orderDO: orderPOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipDetailsDTO.TYPE_ORDER);
            dto.setSoftChannelName(orderDO.getSoftChannelName());
            dto.setType(orderDO.getType());
            dto.setCreateTime(orderDO.getCreateTime());
            dto.setComTypeName(orderDO.getComTypeName());
            dto.setDays(orderDO.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // 好评活动赠送
        List<UserActivityDO>  userActivityDOs = userActivityMapper.queryByUserId(userId);
        for(UserActivityDO userActivityDO: userActivityDOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipDetailsDTO.TYPE_ACTIVITY);
            dto.setCreateTime(userActivityDO.getCreateTime());
            dto.setComTypeName(userActivityDO.getComTypeName());
            dto.setDays(userActivityDO.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // 新用户赠送
        List<NewUserRecordDO> newUserRecordDOs = newUserRecordMapper.queryByUserId(userId);
        for(NewUserRecordDO newUserRecordDO : newUserRecordDOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipDetailsDTO.TYPE_USER_GIFTS);
            dto.setCreateTime(newUserRecordDO.getCreateTime());
            dto.setComTypeName(newUserRecordDO.getComTypeName());
            dto.setDays(newUserRecordDO.getDays());
            userVipDetailsDTOs.add(dto);
        }
        // V商神器赠送

        // 卡密激活
        List<BatchInfoDO> batchInfoDOs = infoMapper.queryByUserId(userId);
        for(BatchInfoDO batchInfoDO: batchInfoDOs) {
            UserVipDetailsDTO dto = new UserVipDetailsDTO();
            dto.setVipType(UserVipDetailsDTO.TYPE_BATCH_INFO);
            dto.setCreateTime(batchInfoDO.getCreateTime());
            dto.setComTypeName(batchInfoDO.getComTypeName());
            dto.setDays(batchInfoDO.getDays());
            userVipDetailsDTOs.add(dto);
        }

        return new DTPageInfo<>(draw, 10, userVipDetailsDTOs);
    }
}
