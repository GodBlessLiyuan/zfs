package com.rpa.web.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PathInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String basePath = scheme + "://" + serverName + ":" + port + path +"/manager";
        request.setAttribute("basePath",basePath);
        return true;
    }
}
