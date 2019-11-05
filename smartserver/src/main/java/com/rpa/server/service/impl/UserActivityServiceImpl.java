package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.UserActivityDTO;
import com.rpa.server.mapper.UserActivityMapper;
import com.rpa.server.pojo.UserActivityPO;
import com.rpa.server.service.IUserActivityService;
import com.rpa.server.utils.UploadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 18:59
 * @description: 用户活动
 * @version: 1.0
 */
@Service
public class UserActivityServiceImpl implements IUserActivityService {
    @Resource
    private UserActivityMapper userActivityMapper;

    @Override
    public ResultVO uploadPic(UserActivityDTO dto) {
        List<UserActivityPO> userActivityPOs = userActivityMapper.queryByUserIdAndStatus(dto.getUd(), (byte) 0);
        for (UserActivityPO po : userActivityPOs) {
            if (20 != po.getStatus()) {
                return new ResultVO(2000);
            }
        }

        UserActivityPO userActivityPO = new UserActivityPO();
        userActivityPO.setActivityId(1);
        userActivityPO.setUserId(dto.getUd());
        userActivityPO.setTime(new Date());
        userActivityPO.setStatus((byte) 1);
        userActivityPO.setCreateTime(new Date());
        userActivityPO.setUserDeviceId(dto.getUdd());
        userActivityPO.setDeviceId(dto.getId());
        userActivityPO.setUrl(UploadUtil.uploadBase64Image(dto.getPicdata1()));
        userActivityMapper.insert(userActivityPO);

        return new ResultVO(1000);
    }
}
