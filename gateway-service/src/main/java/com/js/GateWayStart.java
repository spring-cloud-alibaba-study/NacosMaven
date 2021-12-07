package com.js;

import com.js.util.StartLogo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description 网关启动类
 * @EnableZuulProxy 网关注解
 * @Author jishubu
 */
@Slf4j
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GateWayStart {
    public static void main(String[] args) {
        try {
            SpringApplication.run(GateWayStart.class, args);
            log.info(StartLogo.print());
            log.info("项目启动成功");
        } catch (Exception e) {
            log.info("项目启动失败");
        }

    }
}