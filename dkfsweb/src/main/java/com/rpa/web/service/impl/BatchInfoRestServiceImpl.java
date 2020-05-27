package com.rpa.web.service.impl;

import com.rpa.common.constant.BatchInfoConstant;
import com.rpa.common.mapper.*;
import com.rpa.common.pojo.*;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.dto.BatchInfoDTO;
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
    private ViptypeMapper vipTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activateSync(BatchSycInfoDTO dto) {

        UserPO userPO = userMapper.queryByPhone(dto.getUserDTO().getPhone());
        if(userPO==null){
            userPO=new UserPO();
            UserDouDTO userDTO = dto.getUserDTO();
            UserDouDTO.convertPO(userDTO,userPO);
            userPO.setUserId(null);
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
        return new ResultVO(1000);
    }


    @Autowired
    private BatchInfoMapper batchInfoMapper;
    @Autowired
    private ChBatchMapper chBatchMapper;

    @Autowired
    private ActiveZnzsMapper znzsMapper;

    /**
     * 助手調用多開分身
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO keyActivateZnzj(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        if (null == po) {
            return new ResultVO(1016);
        }

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
            userPO1.setPhone(dto.getPhone());
            userPO1.setUserId(null);
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
        Byte activeSyc=chBatchMapper.queryActiveByPri(po.getBatchId());
        if(activeSyc==1){
            return new ResultVO(1000);
        }
        else if(activeSyc==2)
        {
            userVipPO=userVipMapper.queryByUserId(useID);
            UserPO userPO= userMapper.selectByPrimaryKey(userVipPO.getUserId());
            //用于发送到智能助手的对象batchSycInfoDTO
            BatchSycInfoDTO batchSycInfoDTO=new BatchSycInfoDTO();
            UserDouDTO userDTO=new UserDouDTO();
            UserDouDTO.convertDTO(userDTO,userPO);
            batchSycInfoDTO.setUserDTO(userDTO);
            batchSycInfoDTO.setDay(po.getDays());
            return new ResultVO(999,batchSycInfoDTO);
        }
        else{
            return new ResultVO(2000);
        }
    }


}
