package com.rpa.web.filter;

import com.rpa.web.common.Constant;

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
public class SessionFilter implements Filter {

    // 标示符：表示当前用户未登录
    String NO_LOGIN = "您还未登录！";

    // 不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login", "/entry", "/login/get/checkcode", "/"};
    List<String> excludeList = new ArrayList<>();

    SessionFilter() {
        excludeList.add("/css/");
        excludeList.add("/fonts/");
        excludeList.add("/icons/");
        excludeList.add("/images/");
        excludeList.add("/js/");
        excludeList.add("/plugins/");
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
                    response.getWriter().write(this.NO_LOGIN);
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

        for(String exclude :excludeList ){
            if(uri.startsWith(exclude)){
                return true;
            }
        }
        for (String includeUrl : includeUrls) {
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
