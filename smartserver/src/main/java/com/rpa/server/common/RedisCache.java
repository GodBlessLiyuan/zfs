package com.rpa.server.common;

import com.rpa.server.constant.LoginConstant;
import com.rpa.server.mapper.SoftChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 19:49
 * @description: Redis 缓存操作
 * @version: 1.0
 */
@Component
public class RedisCache {

    @Autowired
    private StringRedisTemplate template;
    @Resource
    private SoftChannelMapper softChannelMapper;

    /**
     * 通过渠道名获取渠道ID
     *
     * @param chanName
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
}
