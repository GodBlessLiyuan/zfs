package com.rpa.rabbit.service.impl;

import com.rpa.rabbit.mapper.UserMapper;
import com.rpa.rabbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 16:04 2019/11/15
 * @version: 1.0.0
 * @description: 新注册用户的通知，查询当日新注册用户，存入Redis
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void newRegister() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int newRegister = this.userMapper.queryTodayNewRegister();
        this.template.opsForValue().set("newRegister" + current_date, String.valueOf(newRegister), 25, TimeUnit.HOURS);
    }
}
