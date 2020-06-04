package com.rpa.web.service.impl;

import com.rpa.common.constant.BatchInfoConstant;

import com.rpa.common.mapper.BatchInfoMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.mapper.ViptypeMapper;
import com.rpa.common.mapper.ChBatchMapper;
import com.rpa.common.mapper.ActiveZnzsMapper;
import com.rpa.common.mapper.SoftChannelMapper;

import com.rpa.common.pojo.SoftChannelPO;
import com.rpa.common.pojo.UserPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.pojo.BatchInfoPO;

import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.dto.BatchSycInfoDTO;
import com.rpa.web.dto.UserDouDTO;
import com.rpa.web.service.IBatchInfoRestService;
import com.rpa.web.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
            LogUtil.info_log(logger, "activity", "插入或更新失败", dto);
        }
        BatchInfoPO batchInfoPO=new BatchInfoPO();
        batchInfoPO.setVipkey(dto.getKey());
        //虛擬的外鍵
        batchInfoPO.setBatchId(1);
        batchInfoPO.setStatus((byte) 2);
        batchInfoPO.setDays(dto.getDay());
        batchInfoPO.setUpdateTime(new Date());
        batchInfoPO.setUserId(newUserVipPO.getUserId());
        int result1 = batchInfoMapper.insert(batchInfoPO);
        if (result1 == 0) {
            LogUtil.info_log(logger, "activity", "更新失败", batchInfoPO);
            return new ResultVO(2000);
        }
        
        return new ResultVO(1000);
    }


    @Autowired
    private BatchInfoMapper batchInfoMapper;
    @Autowired
    private ChBatchMapper chBatchMapper;


    /**
     * 助手調用多開分身
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO keyActivateZnzj(BatchSycInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        Byte activeSyc=chBatchMapper.queryActiveByPri(po.getBatchId());
        if (null == po||activeSyc==null||activeSyc==1) {
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
            LogUtil.info_log(logger, "activate", "更新失败", po);
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
            LogUtil.info_log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
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


}
