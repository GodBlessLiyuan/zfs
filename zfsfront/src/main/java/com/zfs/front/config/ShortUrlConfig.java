package com.zfs.front.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 9:45
 * @description: 短链接
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "shorturl")
public class ShortUrlConfig {
    private String url;
    private String key;
    private String validity;
}
