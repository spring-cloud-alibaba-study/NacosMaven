package com.js.filter;

import com.alibaba.fastjson.JSONObject;
import com.js.enums.ExceptionEnum;
import com.js.response.BaseResponse;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * @classname: MyErrorFilter
 * @desc: 异常过滤器：当请求发送异常时，调用该过滤器
 * @version: 1.0
 **/
@Component
@Slf4j
public class MyErrorFilter extends SendErrorFilter {


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException e = (ZuulException) findZuulException(ctx.getThrowable()).getThrowable();
        log.error("请求失败：", e);
        return JSONObject.toJSONString(BaseResponse.buildFail(ExceptionEnum.NETWORK_ANOMALY_ERRORTYPE));
    }
}