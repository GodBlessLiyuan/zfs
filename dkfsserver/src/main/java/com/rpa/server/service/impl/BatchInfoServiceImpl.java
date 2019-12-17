package com.rpa.server.service.impl;

import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.pojo.BatchInfoPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.BatchInfoConstant;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.mapper.BatchInfoMapper;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.UserVipUtil;
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
    @Resource
    private BatchInfoMapper batchInfoMapper;
    @Resource
    private UserVipMapper userVipMapper;

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
        batchInfoMapper.updateByPrimaryKey(po);

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        if (userVipPO == null) {
            userVipMapper.insert(newUserVipPO);
        } else {
            userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        return new ResultVO(1000);
    }
}
