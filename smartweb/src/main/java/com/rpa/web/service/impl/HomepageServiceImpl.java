package com.rpa.web.service.impl;

import com.rpa.web.mapper.*;
import com.rpa.web.service.HomepageService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
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

        String newRegister = null;
        String newUser = null;
        String dayActiveUser = null;
        String monthActiveUser = null;
        String dayRevenue = null;
        String payCount = null;
        String monthRevenue = null;

        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        newRegister = this.template.opsForValue().get("newRegister" + current_date);
        if (null == newRegister) {
            newRegister = String.valueOf(this.userMapper.queryTodayNewRegister());
        }

        newUser = this.template.opsForValue().get("newUser" + current_date);
        if (null == newUser) {
            newUser = String.valueOf(this.deviceMapper.queryTodayNewUser());
        }

        dayActiveUser = (String) this.template.opsForHash().get("deviceStatistics" +current_date, "dayActiveUser");
        if (dayActiveUser == null) {
            dayActiveUser = String.valueOf(this.deviceStatisticsMapper.queryDayActiveUser());
        }

        monthActiveUser = (String)this.template.opsForHash().get("deviceStatistics" + current_date, "monthActiveUser");
        if (null == monthActiveUser) {
            monthActiveUser = String.valueOf(this.deviceStatisticsMapper.queryMonthActiveUser());
        }

        dayRevenue = (String) this.template.opsForHash().get("revenue" + current_date, "dayRevenue");
        if (null == dayRevenue) {
            dayRevenue = String.valueOf(this.orderMapper.queryDayRevenue()*0.01);
        }

        payCount = (String)this.template.opsForHash().get("revenue" + current_date, "payCount");
        if (null == payCount) {
            payCount = String.valueOf(this.orderMapper.queryPayCount());
        }

        monthRevenue = (String) this.template.opsForHash().get("revenue" + current_date, "monthRevenue");
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

        return ResultVOUtil.success(result);
    }
}
