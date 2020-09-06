package com.js.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *@ClassName GateWayConfig
 *@Description 请描述类的业务用途
 *@Author jiangshuang
 *@Date 2020-09-06 19:31
 *@Version 1.0
 **/
@Component
public class GateWayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }
}
