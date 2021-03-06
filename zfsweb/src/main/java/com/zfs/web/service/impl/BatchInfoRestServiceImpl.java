package com.zfs.web.service.impl;

import com.zfs.common.constant.BatchInfoConstant;

import com.zfs.common.mapper.BatchInfoMapper;
import com.zfs.common.mapper.UserMapper;
import com.zfs.common.mapper.UserVipMapper;
import com.zfs.common.mapper.BuyGiftMapper;
import com.zfs.common.mapper.ChBatchMapper;
import com.zfs.common.mapper.SoftChannelMapper;

import com.zfs.common.pojo.SoftChannelPO;
import com.zfs.common.pojo.UserPO;
import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.pojo.BatchInfoPO;
import com.zfs.common.pojo.BuyGiftPO;

import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.bo.UserToBO;
import com.zfs.web.dto.BatchSycInfoDTO;
import com.zfs.web.dto.UserDouDTO;
import com.zfs.web.service.IBatchInfoRestService;
import com.zfs.web.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-05-21 12:42
 */
@Service
public class BatchInfoRestServiceImpl implements IBatchInfoRestService{
    private final static Logger logger = LoggerFactory.getLogger(BatchInfoRestServiceImpl.class);

    @Resource
    private UserVipMapper userVipMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SoftChannelMapper softChannelMapper;
    @Autowired
    private BuyGiftMapper giftMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activateSync(BatchSycInfoDTO dto) {
        //不需要更新卡信息，因为卡在对方库中
        UserPO userPO = userMapper.queryByPhone(dto.getPhone());
        if(userPO==null){
            userPO=new UserPO();
            UserDouDTO userDouDTO = dto.getUserDouDTO();
            UserDouDTO.convertPO(userDouDTO,userPO);
            //通道信息
            Integer id = softChannelMapper.queryIdbyName(userDouDTO.getChanName());
            if(id==null){
                SoftChannelPO softChannelPO=new SoftChannelPO();
                softChannelPO.setName(userDouDTO.getChanName());
                softChannelPO.setCreateTime(new Date());
                softChannelPO.setExtra("智能助手创建");
                softChannelMapper.insertSelective(softChannelPO);
                id = softChannelMapper.queryIdbyName(userDouDTO.getChanName());
                userPO.setSoftChannelId(id);
            }
            userMapper.insertSelective(userPO);
        }
        long useID=userPO.getUserId();
        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(useID);
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, useID, dto.getDay(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activity", "插入或更新失败", dto);
        }
        BatchInfoPO batchInfoPO=new BatchInfoPO();
        batchInfoPO.setVipkey(dto.getKey());
        //虚拟的外键
        batchInfoPO.setBatchId(1);
        batchInfoPO.setStatus((byte) 2);
        batchInfoPO.setDays(dto.getDay());
        batchInfoPO.setUpdateTime(new Date());
        batchInfoPO.setUserId(newUserVipPO.getUserId());
        int result1 = batchInfoMapper.insert(batchInfoPO);
        if (result1 == 0) {
            LogUtil.log(logger, "activity", "更新失败", batchInfoPO);
            return new ResultVO(2000);
        }
        
        return new ResultVO(1000);
    }


    @Autowired
    private BatchInfoMapper batchInfoMapper;
    @Autowired
    private ChBatchMapper chBatchMapper;


    /**
     * 助手调用多开分身
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO keyActivateZnzj(BatchSycInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        if (null == po){
            return new ResultVO(1016);
        }
        Byte activeSyc=chBatchMapper.queryActiveByPri(po.getBatchId());
        if (activeSyc==null||activeSyc==1) {
            return new ResultVO(1016);
        }
        else if(activeSyc!=2){
            return new ResultVO(2000);
        }
        //activeSyc==2的流程
        if (BatchInfoConstant.FROZEN == po.getStatus()) {
            return new ResultVO(1017);
        } else if (BatchInfoConstant.ACTIVATED == po.getStatus()) {
            return new ResultVO(1020);
        } else if (BatchInfoConstant.EXPIRED == po.getStatus()) {
            return new ResultVO(1021);
        }

        UserPO userPO1 = userMapper.queryByPhone(dto.getPhone());
        if(userPO1==null){
           userPO1=new UserPO();
           UserDouDTO userDouDTO=dto.getUserDouDTO();
           UserDouDTO.convertPO(userDouDTO,userPO1);
           //通道信息
            Integer id = softChannelMapper.queryIdbyName(userDouDTO.getChanName());
            if(id==null){
                SoftChannelPO softChannelPO=new SoftChannelPO();
                softChannelPO.setName(userDouDTO.getChanName());
                softChannelPO.setCreateTime(new Date());
                softChannelPO.setExtra("智能助手创建");
                softChannelMapper.insertSelective(softChannelPO);
                id = softChannelMapper.queryIdbyName(userDouDTO.getChanName());
                userPO1.setSoftChannelId(id);
            }
            userMapper.insertSelective(userPO1);
        }
        po.setUserId(userPO1.getUserId());
        po.setStatus((byte) 2);
        po.setUpdateTime(new Date());
        int result1 = batchInfoMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "activate", "更新失败", po);
            return new ResultVO(2000);
        }

        // 更新用户会员数据
        long useID=userPO1.getUserId();
        UserVipPO userVipPO = userVipMapper.queryByUserId(useID);
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, useID, po.getDays(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
            return new ResultVO(2000);
        }

        if(activeSyc==2)
        {
            //激活助手，传输参数：天数
            BatchSycInfoDTO batchSycInfoDTO=new BatchSycInfoDTO();
            batchSycInfoDTO.setDay(po.getDays());
            return new ResultVO(999,batchSycInfoDTO);
        }
        else{
            return new ResultVO(2000);
        }
    }

    @Override
    public ResultVO bugZJDouOrder(UserToBO dto) {
        logger.info("用户手机号："+dto.getPhone()+",支付类型（1：微信，2：支付宝）："+dto.getType());
        UserPO userPO1 = userMapper.queryByPhone(dto.getPhone());
        if(userPO1==null){
            userPO1=new UserPO();
            userPO1.setUsername(dto.getUsername());
            userPO1.setPhone(dto.getPhone());
            userPO1.setIp(dto.getIp());
            userPO1.setCreateTime(new Date());
            userPO1.setChanName(dto.getChanName());
            //通道信息
            Integer id = softChannelMapper.queryIdbyName(dto.getChanName());
            if(id==null){
                SoftChannelPO softChannelPO=new SoftChannelPO();
                softChannelPO.setName(dto.getChanName());
                softChannelPO.setCreateTime(new Date());
                softChannelPO.setExtra("智能助手创建");
                softChannelMapper.insertSelective(softChannelPO);
                id = softChannelMapper.queryIdbyName(userPO1.getChanName());
            }
            userPO1.setSoftChannelId(id);
            userMapper.insertSelective(userPO1);
        }
        BuyGiftPO buyGiftPO=giftMapper.queryOrder(dto.getOrderNumber());
        if(buyGiftPO!=null){
            LogUtil.log(logger,"bugZJDouOrder","多开分身出现重复调用",dto.toString());
            return new ResultVO(2001);
        }

        // 更新用户会员数据
        long useID=userPO1.getUserId();
        UserVipPO userVipPO = userVipMapper.queryByUserId(useID);
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, useID, dto.getDay(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
            return new ResultVO(2000);
        }
        userVipPO=userVipMapper.queryByUserId(useID);
        //更新赠送会员表，之后再server和web服务查询到
        buyGiftPO=new BuyGiftPO();
        buyGiftPO.setUserId(userPO1.getUserId());
        buyGiftPO.setType(dto.getType());//1微信2支付宝
        buyGiftPO.setCmdyName(dto.getCmdyName());
        buyGiftPO.setComTypeName(dto.getComTypeName());
        buyGiftPO.setCreateTime(new Date());
        buyGiftPO.setDays(dto.getDay());
        buyGiftPO.setComName(dto.getComName());
        buyGiftPO.setStarttime(userVipPO.getEndTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userVipPO.getEndTime());
        calendar.add(Calendar.DATE, dto.getDay());
        Date endDate = calendar.getTime();
        buyGiftPO.setEndtime(endDate);
        buyGiftPO.setOrderNumber(dto.getOrderNumber());
        giftMapper.insertSelective(buyGiftPO);
        logger.info("赠送给多开分身成功");
        return new ResultVO(1000);
    }
}
