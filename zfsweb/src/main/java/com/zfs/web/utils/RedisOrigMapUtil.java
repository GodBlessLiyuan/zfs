package com.zfs.web.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 使用原始工具的缓存
 * @author: liyuan
 * @data 2020-08-25 15:01
 */
@Component
public class RedisOrigMapUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    public void hset(String key, String field, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, field, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }
    public void hset(String key, String field, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, field, JSON.toJSONString(value));
        redisTemplate.expire(key, timeout, timeUnit);
    }

}
