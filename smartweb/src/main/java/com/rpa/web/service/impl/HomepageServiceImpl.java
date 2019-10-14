package com.rpa.web.service.impl;

import com.rpa.web.mapper.OrderMapper;
import com.rpa.web.mapper.UserDeviceMapper;
import com.rpa.web.mapper.UserMapper;
import com.rpa.web.mapper.VipCommodityMapper;
import com.rpa.web.service.HomepageService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
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



    /**
     * 查询
     * @return
     */
    @Override
    public ResultVO query() {

        int newRegister = this.userMapper.queryNewRegister();
        int newUser = this.userDeviceMapper.queryNewUser();
        float dayRevenue = this.vipCommodityMapper.queryRevenue();
        int payCount = this.orderMapper.queryPayCount();
        float monthRevenue = this.vipCommodityMapper.queryMonthRevenue();

        // 创建一个map，存放返回给前端的结果
        Map<String, Object> result = new HashMap<>();
        result.put("newRegister", newRegister);
        result.put("newUser", newUser);
        result.put("dayRevenue", dayRevenue);
        result.put("payCount", payCount);
        result.put("monthRevenue", monthRevenue);

        return ResultVOUtil.success(result);
    }
}
