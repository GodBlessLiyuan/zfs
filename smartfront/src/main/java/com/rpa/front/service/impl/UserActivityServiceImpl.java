package com.rpa.front.service.impl;

import com.rpa.front.dto.UserActivityDTO;
import com.rpa.front.mapper.UserActivityMapper;
import com.rpa.front.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: dangyi
 * @date: Created in 19:02 2019/11/4
 * @version: 1.0.0
 * @description:免费领取会员，根据两个ID查询状态
 */
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Override
    public Integer query(UserActivityDTO dto) {

        Integer status = this.userActivityMapper.query(dto.getActivityId(), dto.getUserId());
        return status;
    }
}
