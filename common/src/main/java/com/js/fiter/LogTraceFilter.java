package com.js.fiter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class LogTraceFilter implements Filter {
    private final String TRACE_ID = "TraceId";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            String traceId = UUID.randomUUID().toString().replace("-", "");
            MDC.put(TRACE_ID, traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error("添加traceId出错，错误信息：", e);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

    @Override
    public void destroy() {

    }
}