package com.zfs.web.config;

import com.zfs.web.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dangyi
 * @date: Created in 18:18 2019/10/25
 * @version: 1.0.0
 * @description:
 */
@Configuration
public class LoginStatusFilterConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        registration.setFilter(new SessionFilter());
        // 设置过滤器的URL模式：过滤所有请求
        registration.addUrlPatterns("/*");
        return registration;
    }
}
