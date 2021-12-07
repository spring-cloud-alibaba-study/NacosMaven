package com.js.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器类型
 */
@Component
@Slf4j
public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * zuul-core中的FilterLoader.java的getFilterByType中按类型pre、route、post取Filter后，再按照FilterOrder进行排序。
     * 先执行pre>routing>post 然后再在同类型的过滤器按照order大小执行，越小的越先被执行    @Override
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否执行，false不执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义执行逻辑
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info("自定义zuulFilter:" + request.getRequestURI());

        return null;
    }
}