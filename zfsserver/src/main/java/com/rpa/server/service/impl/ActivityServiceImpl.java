package com.rpa.server.service.impl;

import com.rpa.common.mapper.ActivityMapper;
import com.rpa.common.mapper.UserActivityMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.pojo.ActivityPO;
import com.rpa.common.pojo.UserActivityPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ActivityDTO;
import com.rpa.server.service.IActivityService;
import com.rpa.server.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

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
        int result;
        if (userVipPO == null) {
            result = userVipMapper.insert(newUserVipPO);
        } else {
            result = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result == 0) {
            LogUtil.log(logger, "activate", "插入或更新失败", newUserVipPO);
        }

        return new ResultVO<>(1000);
    }
}
