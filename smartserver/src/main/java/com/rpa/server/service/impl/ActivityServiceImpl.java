package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.ActivityDTO;
import com.rpa.server.mapper.UserActivityMapper;
import com.rpa.server.pojo.UserActivityPO;
import com.rpa.server.service.IActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 9:13
 * @description: 活动
 * @version: 1.0
 */
@Service
public class ActivityServiceImpl implements IActivityService {
    @Resource
    private UserActivityMapper userActivityMapper;

    @Override
    public ResultVO check(ActivityDTO dto) {
        List<UserActivityPO> pos = userActivityMapper.queryPassedByUserId(dto.getUd());
        if (pos == null || pos.size() == 0) {
            return new ResultVO(1015);
        }

        return new ResultVO<>(1000);
    }

    @Override
    public ResultVO activate(ActivityDTO dto) {
        List<UserActivityPO> pos = userActivityMapper.queryPassedByUserId(dto.getUd());
        if (pos == null || pos.size() == 0) {
            return new ResultVO(1015);
        }

        userActivityMapper.activate(dto.getUd());

        return new ResultVO<>(1000);
    }
}
