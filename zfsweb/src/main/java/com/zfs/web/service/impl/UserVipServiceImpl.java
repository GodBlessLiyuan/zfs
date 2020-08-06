package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.BatchInfoBO;
import com.zfs.common.bo.NewUserRecordBO;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.bo.UserActivityBO;
import com.zfs.common.bo.UserVipBO;
import com.zfs.common.bo.BuyGiftBO;
import com.zfs.common.mapper.*;
import com.zfs.common.pojo.GodinsecUserPO;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.common.UserVipConstant;
import com.zfs.web.vo.UserVipVO;
import com.zfs.web.vo.UserVipDetailsVO;
import com.zfs.web.service.IUserVipService;
import com.zfs.web.utils.DTPageInfo;
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
    @Resource
    private BuyGiftMapper giftMapper;
    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<UserVipBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserVipBO> dos = userVipMapper.query(reqData);
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), UserVipVO.convert(dos)));
    }

    @Override
    public ResultVO queryDetails(long userId) {
        List<UserVipDetailsVO> detailsVOs = new ArrayList<>();
        // 购买
        List<OrderBO> orderBOs = orderMapper.queryByUserId(userId);
        for (OrderBO bo : orderBOs) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_ORDER);
            dto.setUserChanName(bo.getUserChanName());
            if(bo.getCommAttr()==null||bo.getCommAttr()==1){
                dto.setCommAttr("独立商品");
            }else if(bo.getCommAttr()==2){
                dto.setCommAttr("通用商品");
            }
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
            dto.setCommAttr("无");
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
            dto.setCommAttr("无");
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(bo.getCreateTime());
            dto.setComTypeName(bo.getComTypeName());
            dto.setDays(bo.getDays());
            detailsVOs.add(dto);
        }

        // 卡密激活
        List<BatchInfoBO> batchInfoBOS = infoMapper.queryByUserId(userId);
        for (BatchInfoBO batchInfoBO : batchInfoBOS) {
            UserVipDetailsVO dto = new UserVipDetailsVO();
            dto.setVipType(UserVipConstant.USER_VIP_BATCH);
            dto.setUserChanName(batchInfoBO.getUserChanName());
            dto.setCommAttr("无");
            dto.setSaleChanName(UserVipConstant.DEFAULT_SALE_CHAN_NAME);
            dto.setCreateTime(batchInfoBO.getUpdateTime());
            dto.setComTypeName(batchInfoBO.getComTypeName());
            dto.setDays(batchInfoBO.getDays());
            detailsVOs.add(dto);
        }

        // 根据购买时间降序排序
        Collections.sort(detailsVOs);

        return new ResultVO(1000,  detailsVOs);
    }
}
