package com.zfs.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zfs.common.mapper.SoftChannelMapper;
import com.zfs.common.mapper.WhiteDeviceMapper;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.server.constant.LoginConstant;
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
    private WhiteDeviceMapper whiteDeviceMapper;

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
        String redisKey = RedisKeyUtil.genWhiteDeviceRedisKey(devId);
        Set<String> cacheDevIds = template.opsForSet().members(redisKey);
        if (cacheDevIds == null || cacheDevIds.size() == 0) {
            Set<String> devIds = whiteDeviceMapper.queryAllDevId();
            if (devIds == null || devIds.size() == 0) {
                return false;
            }
            template.opsForSet().add(redisKey, devIds.toArray(new String[0]));
            template.expire(redisKey, 1, TimeUnit.DAYS);
            return devIds.contains(String.valueOf(devId));
        }

        return cacheDevIds.contains(String.valueOf(devId));
    }

    /**
     * 从缓存中取数据
     *
     * @param key
     * @return
     */
    public String getCacheByKey(Object key) {
        return template.opsForValue().get(key);
    }

    /**
     * 在缓存中设置数据
     *
     * @param key
     * @param value
     */
    public void setCache(String key, Object value, long num, TimeUnit time) {
        template.opsForValue().set(key, JSON.toJSONString(value), num, time);
    }

    /**
     * 在缓存中设置数据，数据中涵盖有日期
     *
     * @param key
     * @param value
     */
    public void setCacheWithDate(String key, Object value, long num, TimeUnit time) {
        template.opsForValue().set(key, JSON.toJSONStringWithDateFormat(value, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat), num, time);
    }
}
