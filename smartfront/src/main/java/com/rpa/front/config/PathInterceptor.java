package com.rpa.front.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PathInterceptor implements HandlerInterceptor {

    private String projectBaseHttpUrl;
    private String projectBaseHttpsUrl;
    private boolean isHttps;

    public PathInterceptor(String projectBaseUrl) {
        isHttps = projectBaseUrl.toLowerCase().startsWith("https");
        if (isHttps) {
            this.projectBaseHttpsUrl = projectBaseUrl;
            this.projectBaseHttpUrl = projectBaseUrl.replace("https", "http");
        } else {
            this.projectBaseHttpUrl = projectBaseUrl;
            this.projectBaseHttpsUrl = projectBaseUrl.replace("http", "https");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String scheme = request.getScheme();
        if (scheme.toLowerCase().startsWith("https")) {
            request.setAttribute("basePath", this.projectBaseHttpsUrl);
        } else {
            request.setAttribute("basePath", this.projectBaseHttpUrl);
        }

        return true;
    }
}
