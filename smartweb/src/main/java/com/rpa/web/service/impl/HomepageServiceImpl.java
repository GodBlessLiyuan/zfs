package com.rpa.web.service.impl;

import com.rpa.web.mapper.*;
import com.rpa.web.service.HomepageService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

        newRegister = this.template.opsForValue().get("newRegister");
        if (null == newRegister) {
            newRegister = String.valueOf(this.userMapper.queryTodayNewRegister());
        }

        newUser = this.template.opsForValue().get("newUser");
        if (null == newUser) {
            newUser = String.valueOf(this.deviceMapper.queryTodayNewUser());
        }

        dayActiveUser = this.template.opsForList().range("deviceStatistics", 0, -1).get(0);
        if (dayActiveUser == null) {
            dayActiveUser = String.valueOf(this.deviceStatisticsMapper.queryDayActiveUser());
        }

        monthActiveUser = this.template.opsForList().range("deviceStatistics", 0, -1).get(1);
        if (null == monthActiveUser) {
            monthActiveUser = String.valueOf(this.deviceStatisticsMapper.queryMonthActiveUser());
        }

        dayRevenue = this.template.opsForList().range("revenue", 0, -1).get(0);
        if (null == dayRevenue) {
            dayRevenue = String.valueOf(this.orderMapper.queryDayRevenue());
        }

        payCount = this.template.opsForList().range("revenue", 0, -1).get(1);
        if (null == payCount) {
            payCount = String.valueOf(this.orderMapper.queryPayCount());
        }

        monthRevenue = this.template.opsForList().range("revenue", 0, -1).get(2);
        if (null == monthRevenue) {
            monthRevenue = String.valueOf(this.orderMapper.queryMonthRevenue());
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
