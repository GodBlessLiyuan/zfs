package com.rpa.front.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author: velve
 * @date: Created in 2019/11/25 23:00
 * @description: TODO
 * @version: 1.0
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
public class SessionConfig {
}
