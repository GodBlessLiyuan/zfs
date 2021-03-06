package com.zfs.web.filter;

import com.zfs.common.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 16:59 2019/10/25
 * @version: 1.0.0
 * @description:
 */
@Component
public class SessionFilter implements Filter {

    private static String CONTEXT_PATH ;
    @Value("${server.servlet.context-path}")
    private void setContextPath(String contextPath){
        CONTEXT_PATH=contextPath;
    }
    // 不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login", "/entry", "/login/get/checkcode", "/", "/favicon.ico"};
    List<String> excludeList = new ArrayList<>();

    public SessionFilter() {
        excludeList.add("/css/");
        excludeList.add("/fonts/");
        excludeList.add("/icons/");
        excludeList.add("/images/");
        excludeList.add("/js/");
        excludeList.add("/plugins/");
        excludeList.add("/actuator/");
        excludeList.add("/login/get/checkcode");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // 判断是否需对访问路径进行过滤
        boolean needFilter = isNeedFilter(uri);

        // 不需要过滤就放行
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // session中包含user对象，则是登录状态，放行
            if (session != null && session.getAttribute(Constant.ADMIN_USER) != null) {
                filterChain.doFilter(request, response);
            } else {
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                    if (requestType != null && "XMLHttpRequest".equals(requestType)) {
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write("{\"code\": 1008}");
                } else {
                    //重定向到登录页
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                return;
            }
        }
    }

    /**
     * 判断是否需要过滤
     *
     * @param uri
     */
    public boolean isNeedFilter(String uri) {
        for (String exclude : excludeList) {
            exclude = CONTEXT_PATH + exclude;
            if (uri.startsWith(exclude)) {
                return false;
            }
        }
        for (String includeUrl : includeUrls) {
            includeUrl = CONTEXT_PATH + includeUrl;
            if (includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
