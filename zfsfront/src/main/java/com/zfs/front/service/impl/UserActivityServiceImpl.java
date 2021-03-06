package com.zfs.front.service.impl;

import com.zfs.common.mapper.UserActivityMapper;
import com.zfs.front.dto.UserActivityDTO;
import com.zfs.front.service.UserActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: dangyi
 * @date: Created in 19:02 2019/11/4
 * @version: 1.0.0
 * @description:免费领取会员，根据两个ID查询状态
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Resource
    private UserActivityMapper userActivityMapper;

    @Override
    public Integer query(UserActivityDTO dto) {

        Integer status = this.userActivityMapper.queryByUd(dto.getUd());
        return status;
    }
}
