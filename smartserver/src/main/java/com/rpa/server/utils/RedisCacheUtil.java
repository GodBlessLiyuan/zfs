package com.rpa.server.utils;

import com.alibaba.fastjson.JSON;
import com.rpa.server.constant.LoginConstant;
import com.rpa.server.mapper.SoftChannelMapper;
import com.rpa.server.mapper.WhilteDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 19:49
 * @description: Redis 缓存操作
 * @version: 1.0
 */
@Component
public class RedisCacheUtil {

    @Autowired
    private StringRedisTemplate template;
    @Resource
    private SoftChannelMapper softChannelMapper;
    @Resource
    private WhilteDeviceMapper whilteDeviceMapper;

    /**
     * 通过渠道名获取渠道ID
     *
     * @param chanName 渠道名称
     * @return
     */
    public Integer getSoftChannelId(String chanName) {
        String chanId = this.template.opsForValue().get(chanName);
        if (null != chanId) {
            return Integer.parseInt(chanId);
        }

        Integer id = softChannelMapper.queryIdbyName(chanName);
        if (null != id) {
            this.template.opsForValue().set(chanName, String.valueOf(id), 5, TimeUnit.HOURS);
            return id;
        }

        return 0;
    }

    /**
     * 缓存手机验证码
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public void cacheVerifyCode(String phone, String code) {
        template.opsForValue().set(LoginConstant.VERIFY_CODE_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    /**
     * 短信码校验
     *
     * @param phone 手机号
     * @param sms   短信码
     * @return 返回码
     */
    public int checkSmsByCache(String phone, String sms) {
        if (sms == null || sms.length() != LoginConstant.VERIFY_CODE_LENGTH) {
            return 1014;
        }
        String cacheSms = template.opsForValue().get(LoginConstant.VERIFY_CODE_PREFIX + phone);
        if (cacheSms == null) {
            return 1013;
        }
        if (!sms.equals(cacheSms)) {
            return 1014;
        }

        return 1000;
    }

    /**
     * 获取缓存白名单数据
     *
     * @return
     */
    public boolean checkWhiteDeviceByDevId(long devId) {
        Set<String> cacheDevIds = template.opsForSet().members("whiteDevIds");
        if (cacheDevIds == null || cacheDevIds.size() == 0) {
            Set<Long> devIds = whilteDeviceMapper.queryAllDevId();
            template.opsForSet().add("whiteDevIds", JSON.toJSONString(devIds));
            template.expire("whiteDevIds", 1, TimeUnit.DAYS);
            return devIds.contains(devId);
        }

        return cacheDevIds.contains(String.valueOf(devId));
    }
}
