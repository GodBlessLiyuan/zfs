package com.rpa.rabbit.service.impl;

import com.rpa.rabbit.service.DeviceMapper;
import com.rpa.rabbit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 19:27 2019/11/15
 * @version: 1.0.0
 * @description:
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void newUser() {
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int newUser = this.deviceMapper.queryTodayNewUser();
        this.template.opsForValue().set("newUser" + current_date, String.valueOf(newUser), 25, TimeUnit.HOURS);
    }
}
