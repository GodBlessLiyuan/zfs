package com.rpa.web.service.impl;

import com.rpa.common.mapper.DeviceMapper;
import com.rpa.common.mapper.OrderMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.mapper.*;
import com.rpa.web.service.HomepageService;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 11:39 2019/10/14
 * @version: 1.0.0
 * @description:
 */
@Service
public class HomepageServiceImpl implements HomepageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DeviceStatisticsMapper deviceStatisticsMapper;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 主页查询：先去Redis中查询，无果，再去数据库查询
     * @return
     */
    @Override
    public ResultVO query() {

        //当前日期
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //新增注册用户
        String newRegister;
        String key_newRegister = RedisKeyUtil.genHomepageRedisKey() + "newRegister" + current_date;
        newRegister = this.template.opsForValue().get(key_newRegister);
        if (null == newRegister) {
            newRegister = String.valueOf(this.userMapper.queryTodayNewRegister());
        }

        //新用户数
        String newUser;
        String key_newUser = RedisKeyUtil.genHomepageRedisKey() + "newUser" + current_date;
        newUser = this.template.opsForValue().get(key_newUser);
        if (null == newUser) {
            newUser = String.valueOf(this.deviceMapper.queryTodayNewUser());
        }


        String key_active = RedisKeyUtil.genHomepageRedisKey() + "deviceStatistics" + current_date;
        //日活跃用户数
        String dayActiveUser;
        dayActiveUser = (String) this.template.opsForHash().get(key_active, "dayActiveUser");
        if (dayActiveUser == null) {
            dayActiveUser = String.valueOf(this.deviceStatisticsMapper.queryDayActiveUser());
        }

        //月活跃用户数
        String monthActiveUser;
        monthActiveUser = (String)this.template.opsForHash().get(key_active, "monthActiveUser");
        if (null == monthActiveUser) {
            monthActiveUser = String.valueOf(this.deviceStatisticsMapper.queryMonthActiveUser());
        }


        String key_revenue = RedisKeyUtil.genHomepageRedisKey() + "revenue" + current_date;
        //当日收入
        String dayRevenue;
        dayRevenue = (String) this.template.opsForHash().get(key_revenue, "dayRevenue");
        if (null == dayRevenue) {
            dayRevenue = String.valueOf(this.orderMapper.queryDayRevenue()*0.01);
        }

        //支付次数
        String payCount;
        payCount = (String)this.template.opsForHash().get(key_revenue, "payCount");
        if (null == payCount) {
            payCount = String.valueOf(this.orderMapper.queryPayCount());
        }

        //当月收入
        String monthRevenue;
        monthRevenue = (String) this.template.opsForHash().get(key_revenue, "monthRevenue");
        if (null == monthRevenue) {
            monthRevenue = String.valueOf(this.orderMapper.queryMonthRevenue()*0.01);
        }

        // 创建一个map，存放返回给前端的结果
        Map<String, Object> result = new HashMap<>();
        result.put("newRegister", newRegister);
        result.put("newUser", newUser);
        result.put("dayActiveUser", dayActiveUser);
        result.put("monthActiveUser", monthActiveUser);
        result.put("dayRevenue", dayRevenue);
        result.put("payCount", payCount);
        result.put("monthRevenue", monthRevenue);

        return new ResultVO(1000, result);
    }
}
