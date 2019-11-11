package com.rpa.web.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PathInterceptor implements HandlerInterceptor {
    private static final String CONTEXT_PATH = "/manager";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String basePath = scheme + "://" + serverName + ":" + port + path + CONTEXT_PATH;
        request.setAttribute("basePath", basePath);
        return true;
    }
}
