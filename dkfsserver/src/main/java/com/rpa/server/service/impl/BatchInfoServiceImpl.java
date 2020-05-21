package com.rpa.server.service.impl;

import com.rpa.common.mapper.BatchInfoMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.mapper.ViptypeMapper;
import com.rpa.common.pojo.BatchInfoPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.BatchInfoConstant;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:28
 * @description: 卡密表
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class BatchInfoServiceImpl implements IBatchInfoService {
    private final static Logger logger = LoggerFactory.getLogger(BatchInfoServiceImpl.class);

    @Resource
    private BatchInfoMapper batchInfoMapper;
    @Resource
    private UserVipMapper userVipMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ViptypeMapper vipTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activate(BatchInfoDTO dto) {
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

        po.setUserId(dto.getUd());
        po.setStatus((byte) 2);
        po.setUpdateTime(new Date());
        int result1 = batchInfoMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "activate", "更新失败", po);
        }

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
        }
        return new ResultVO(1000);
    }


}
