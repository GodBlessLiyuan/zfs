package com.zfs.pay.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-13 20:34
 */
@Component
public class RedisMapUtil {
    private RedisTemplate<String, String> redisTemplate;

    public RedisMapUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 添加or更新hash的值
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value,long timeout,TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, field, JSON.toJSONString(value));
        redisTemplate.expire(key, timeout, timeUnit);
    }
    /**
     * 获取hash中field对应的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        Object val = redisTemplate.opsForHash().get(key, field);
        return val == null ? null : val.toString();
    }
    /**
     * 获取list
     * @param key
     * @param field
     * @return
     */
    public List hgetList(String key, String field){
        String mapStr = hget(key,field);
        if (!StringUtils.isEmpty(mapStr)) {
            return StringToObjUtil.strToObj(mapStr, List.class);
        }else {
            return null;
        }
    }
    /**
     * 删除hash中field这一对kv
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    public void hdel(String key){
        try {
            redisTemplate.delete(key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
