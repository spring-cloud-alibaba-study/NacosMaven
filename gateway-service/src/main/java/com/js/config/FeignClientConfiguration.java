package com.js.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 扫描服务路径并加载
 * @Author: 渡劫 dujie
 * @Date: 2021/4/20 9:48 PM
 */
@Configuration
@EnableFeignClients(basePackages = "com.js")
public class FeignClientConfiguration {
}
