package com.rpa.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

/**
 * @author: xiahui
 * @date: Created in 2019/12/13 18:57
 * @description: TODO
 * @version: 1.0
 */
@Component
public class UrlRedirectFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(UrlRedirectFilter.class);

    @Value("${zuul.config.gray}")
    private boolean gray;
    @Value("${zuul.config.redirect")
    private String redirect;

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String gray = req.getHeader("gray");
        if (this.gray && "aaa".equals(gray)) {
            ctx.setRouteHost(new URL(this.redirect));
            String url = req.getRequestURI();
            logger.info(url);
        }
        return null;
    }
}
