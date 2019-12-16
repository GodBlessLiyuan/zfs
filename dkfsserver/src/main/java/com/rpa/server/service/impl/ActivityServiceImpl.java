package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.ActivityDTO;
import com.rpa.server.mapper.ActivityMapper;
import com.rpa.server.mapper.UserActivityMapper;
import com.rpa.server.mapper.UserVipMapper;
import com.rpa.server.pojo.ActivityPO;
import com.rpa.server.pojo.UserActivityPO;
import com.rpa.server.pojo.UserVipPO;
import com.rpa.server.service.IActivityService;
import com.rpa.server.utils.UserVipUtil;
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
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private UserVipMapper userVipMapper;

    @Override
    public ResultVO check(ActivityDTO dto) {
        List<UserActivityPO> pos = userActivityMapper.queryByUserIdAndStatus(dto.getUd(), (byte) 10);
        if (pos == null || pos.size() == 0) {
            return new ResultVO(1015);
        }

        return new ResultVO<>(1000);
    }

    @Override
    public ResultVO activate(ActivityDTO dto) {
        List<UserActivityPO> pos = userActivityMapper.queryByUserIdAndStatus(dto.getUd(), (byte) 10);
        if (pos == null || pos.size() == 0) {
            return new ResultVO(1015);
        }

        userActivityMapper.activate(dto.getUd());

        // 更新用户会员数据
        ActivityPO activityPO = activityMapper.selectByPrimaryKey(pos.get(0).getActivityId());
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), activityPO.getDays(), false);
        if (userVipPO == null) {
            userVipMapper.insert(newUserVipPO);
        } else {
            userVipMapper.updateByPrimaryKey(newUserVipPO);
        }

        return new ResultVO<>(1000);
    }
}
