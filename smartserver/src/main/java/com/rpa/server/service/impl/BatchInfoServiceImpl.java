package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.mapper.BatchInfoMapper;
import com.rpa.server.mapper.UserVipMapper;
import com.rpa.server.pojo.BatchInfoPO;
import com.rpa.server.pojo.UserVipPO;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.UserVipUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:28
 * @description: 卡密表
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class BatchInfoServiceImpl implements IBatchInfoService {
    @Resource
    private BatchInfoMapper batchInfoMapper;
    @Resource
    private UserVipMapper userVipMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activate(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByUserIdAndKey(0L, dto.getKey());
        if (po == null) {
            return new ResultVO(1016);
        }
        if (po.getStatus() != 1) {
            return new ResultVO(1017);
        }

        po.setUserId(dto.getUd());
        po.setStatus((byte) 2);
        batchInfoMapper.updateByPrimaryKey(po);

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        if(userVipPO == null) {
            userVipMapper.insert(newUserVipPO);
        }else {
            userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        return new ResultVO(1000);
    }
}