package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.mapper.BatchInfoMapper;
import com.rpa.server.pojo.BatchInfoPO;
import com.rpa.server.service.IBatchInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:28
 * @description: 卡密表
 * @version: 1.0
 */
@Service
public class BatchInfoServiceImpl implements IBatchInfoService {
    @Resource
    private BatchInfoMapper batchInfoMapper;

    @Override
    public ResultVO activate(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByUserIdAndKey(dto.getUd(), dto.getKey());
        if (po == null) {
            return new ResultVO(1016);
        }
        if (po.getStatus() != 2) {
            return new ResultVO(1017);
        }

        po.setStatus((byte) 1);
        batchInfoMapper.updateByPrimaryKey(po);

        return new ResultVO(1000);
    }
}
