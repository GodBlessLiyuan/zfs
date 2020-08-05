package com.zfs.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathInterceptorConfig implements WebMvcConfigurer {

    @Value("${project.baseurl}")
    private String projectBaseUrl;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PathInterceptor(projectBaseUrl)).addPathPatterns("/**");
    }
}
