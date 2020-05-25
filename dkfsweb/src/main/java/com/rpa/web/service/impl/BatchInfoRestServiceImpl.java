package com.rpa.web.service.impl;

import com.rpa.common.dto.BatchSycInfoDTO;
import com.rpa.common.mapper.BatchInfoMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.mapper.ViptypeMapper;
import com.rpa.common.pojo.UserPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.pojo.ViptypePO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.service.IBatchInfoRestService;
import com.rpa.web.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

        //不需要更新卡信息，因为卡在对方库中
        ViptypePO vipTypePO = vipTypeMapper.queryName(dto.getVipTypePO().getVipname());
        if(vipTypePO==null){
            vipTypePO=new ViptypePO();
            vipTypePO=dto.getVipTypePO();
            vipTypeMapper.insert(vipTypePO);
        }
        UserPO userPO = userMapper.queryByPhone(dto.getUserPO().getPhone());
        if(userPO==null){
            userPO=new UserPO();
            userPO=dto.getUserPO();
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

}
