package com.rpa.front.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PathInterceptor implements HandlerInterceptor {

    private String projectBaseUrl;

    public PathInterceptor(String projectBaseUrl){
        this.projectBaseUrl = projectBaseUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("basePath", projectBaseUrl);
        return true;
    }
}
