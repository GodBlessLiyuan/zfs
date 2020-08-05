package com.rpa.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/11/12 19:24
 * @description: 缓存
 * @version: 1.0
 */
@RestController
public class CacheController {
    @Autowired
    private StringRedisTemplate template;

    @PostMapping("delete")
    public void delete(@RequestBody String key) {
        template.delete(key);
    }
}
