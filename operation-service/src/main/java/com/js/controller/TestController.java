package com.js.controller;

import com.js.dubbo.TestDubboService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: NacosMaven
 * @Date: 2020/9/2 16:26
 * @Author: jiangshuang
 * @Description:
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestDubboService testDubboService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/test")
    public String getString() {
        log.info("进入消费者");
        RLock lock = redissonClient.getLock("test1");
        try {
            if (lock.tryLock(0, 2000, TimeUnit.SECONDS)) {
                log.info("获取分布式锁成功");
                Thread.sleep(9000);
                return testDubboService.sayHello("jiangshuang");
            }
            throw new RuntimeException("获取分布式锁失败");

        } catch (Exception e) {
            log.error("调用dubbo出现异常", e);
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
