package com.js;

import com.js.util.StartLogo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description 启动类
 * @Author jishubu
 * @ComponentScan
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class OperationStart {
    public static void main(String[] args) {
        try {
            SpringApplication.run(OperationStart.class, args);
            log.info(StartLogo.print());
        } catch (Exception e) {
            log.info("项目启动失败");
        }
    }
}
