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
    private UserDeviceMapper userDeviceMapper;

    @Autowired
    private VipCommodityMapper vipCommodityMapper;

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

        int newRegister = this.userMapper.queryNewRegister();
        int newUser = this.userDeviceMapper.queryNewUser();
        int dayActiveUser = this.deviceStatisticsMapper.queryDayActiveUser();
        int monthActiveUser = this.deviceStatisticsMapper.queryMonthActiveUser();
        Float dayRevenue = this.vipCommodityMapper.queryRevenue();
        int payCount = this.orderMapper.queryPayCount();
        Float monthRevenue = this.vipCommodityMapper.queryMonthRevenue();

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
