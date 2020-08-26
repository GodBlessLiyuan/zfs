package com.zfs.web.service.impl;

import com.zfs.common.mapper.DeviceMapper;
import com.zfs.common.mapper.DeviceStatisticsMapper;
import com.zfs.common.mapper.OrderMapper;
import com.zfs.common.mapper.UserMapper;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.web.service.HomepageService;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.MultiUtil;
import com.zfs.web.utils.RedisOrigMapUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 11:39 2019/10/14
 * @version: 1.0.0
 * @description:
 */
@Service
public class HomepageServiceImpl implements HomepageService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private DeviceStatisticsMapper deviceStatisticsMapper;

    @Resource
    private StringRedisTemplate template;
    @Resource
    private RedisOrigMapUtil redisOrigMapUtil;
    /**
     * 主页查询：先去Redis中查询，无果，再去数据库查询
     * @return
     */
    @Override
    public ResultVO query() {

        //当前日期
        String current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String key_user = RedisKeyUtil.genHomepageRedisKey("user", current_date);
        //新增注册用户
        String newRegister= (String) this.template.opsForHash().get(key_user, "newRegister");
        if (null == newRegister) {
            newRegister = String.valueOf(this.userMapper.queryTodayNewRegister());
            this.redisOrigMapUtil.hset(key_user,"newRegister",newRegister,10, TimeUnit.MINUTES);
        }

        //新用户数
        String newUser= (String) this.template.opsForHash().get(key_user, "newUser");
        if (null == newUser) {
            newUser = String.valueOf(this.deviceMapper.queryTodayNewUser());
            this.redisOrigMapUtil.hset(key_user,"newUser",newUser,10, TimeUnit.MINUTES);
        }
        String regisConvert=(String) this.template.opsForHash().get(key_user, "regisConvert");
        if(null==regisConvert){
            regisConvert = MultiUtil.strBystr(newRegister, newUser);
            this.redisOrigMapUtil.hset(key_user,"regisConvert",regisConvert,10, TimeUnit.MINUTES);
        }

        String key_active = RedisKeyUtil.genHomepageRedisKey("deviceStatistics", current_date);
        //日活跃用户数
        String dayActiveUser = (String) this.template.opsForHash().get(key_active, "dayActiveUser");
        if (null == dayActiveUser) {
            dayActiveUser = String.valueOf(this.deviceStatisticsMapper.queryDayActiveUser());
            this.redisOrigMapUtil.hset(key_active, "dayActiveUser",dayActiveUser,1,TimeUnit.MINUTES);
        }

        //月活跃用户数
        String monthActiveUser= (String)this.template.opsForHash().get(key_active, "monthActiveUser");
        if (null == monthActiveUser) {
            monthActiveUser = String.valueOf(this.deviceStatisticsMapper.queryMonthActiveUser());
            this.redisOrigMapUtil.hset(key_active, "monthActiveUser",monthActiveUser,10,TimeUnit.MINUTES);
        }


        String key_revenue = RedisKeyUtil.genHomepageRedisKey( "revenue", current_date);
        //当日收入
        String dayRevenue = (String) this.template.opsForHash().get(key_active, "dayRevenue");
        if (null == dayRevenue) {
            dayRevenue = String.valueOf(decimal(this.orderMapper.queryDayRevenue()*0.01));
            this.redisOrigMapUtil.hset(key_active, "dayRevenue",dayRevenue,10,TimeUnit.MINUTES);
        }

        //支付次数
        String payCount= (String)this.template.opsForHash().get(key_active, "payCount");
        if (null == payCount) {
            payCount = String.valueOf(this.orderMapper.queryPayCount());
            this.redisOrigMapUtil.hset(key_active, "payCount",payCount,10,TimeUnit.MINUTES);
        }

        //当月收入
        String monthRevenue= (String) this.template.opsForHash().get(key_active, "monthRevenue");
        if (null == monthRevenue) {
            monthRevenue = String.valueOf(decimal(this.orderMapper.queryMonthRevenue()*0.01));
            this.redisOrigMapUtil.hset(key_active, "monthRevenue",monthRevenue,10,TimeUnit.MINUTES);
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
        result.put("regisConvert",regisConvert);
        return new ResultVO(1000, result);
    }

    /**
     * 保留两位小数
     * @param d
     * @return
     */
    private Double decimal(Double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
