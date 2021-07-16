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
            Boolean temp = lock.tryLock(100, 200, TimeUnit.SECONDS);
            log.info("当前锁的状态{}",lock.isLocked());
            log.info("获取分布式锁的结果为{}", temp);
            if (temp) {
                wait(9000);
                return testDubboService.sayHello("jiangshuang");
            }
            log.warn("获取分布式锁失败");

        } catch (Exception e) {
            log.info("调用dubbo出现异常");
        } finally {
            lock.unlock();
        }
        return null;
    }
}
