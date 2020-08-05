package com.rpa.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/11/12 19:16
 * @description: Cache Feign 接口
 * @version: 1.0
 */
@FeignClient(name = "dkfscache")
public interface CacheFeignClient {
    /**
     * 删除对应缓存
     *
     * @param key
     */
    @PostMapping("delete")
    void delete(String key);
}
