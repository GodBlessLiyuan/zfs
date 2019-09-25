package com.rpa.web.service.impl;

import com.rpa.web.mapper.IUserMapper;
import com.rpa.web.pojo.UserPO;
import com.rpa.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:03
 * @description: TODO
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public List<UserPO> list() {
        return userMapper.list();
    }
}
