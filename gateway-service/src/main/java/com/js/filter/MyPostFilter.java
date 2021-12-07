package com.js.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.js.response.BaseResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @classname: MyErrorFilter
 * @desc: 后置过滤器：检验请求是否成功
 * @version: 1.0
 **/
@Component
@Slf4j
public class MyPostFilter extends ZuulFilter {


    @Autowired
    private SendResponseFilter sendResponseFilter;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        try {
            log.info("当前返回结果为{},响应码为{}", JSONObject.toJSONString(response.getOutputStream()), response.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //只处理非200
        if (response.getStatus() != HttpStatus.OK.value()) {
            if (response.getStatus() != HttpStatus.UNAUTHORIZED.value()) {
                //返回状态非401的都改为200
                response.setStatus(HttpStatus.OK.value());
            }
            try {
                IOUtils.write(JSON.toJSONString(BaseResponse.buildFail()).getBytes(StandardCharsets.UTF_8), response.getOutputStream());
            } catch (Exception e) {
                return null;
            }

        } else {
            //使用默认的post拦截器
            sendResponseFilter.run();
        }
        return null;
    }

}