package com.js.controller;

import com.js.config.TestProperties;
import com.js.distributed.DistributedRedisLock;
import com.js.enums.ExceptionEnum;
import com.js.exception.SystemException;
import com.js.feignclient.UserTestProxy;
import com.js.response.BaseResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
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

    @Resource
    private DistributedRedisLock distributedRedisLock;

    @Autowired
    private UserTestProxy userTestProxy;

    @Autowired
    private ExecutorService commonThreadPool;
    @Autowired
    private TestProperties testProperties;

    @GetMapping("/test")
    public BaseResponse<String> getString() {

//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
//                    System.out.println("1000000");
//                    return "test";
//                }, commonThreadPool).thenComposeAsync(a -> CompletableFuture.supplyAsync(() -> {
//                    System.out.println(123);
//                    return a + "132";
//                }, commonThreadPool))
//                .applyToEither(CompletableFuture.supplyAsync(() -> "test2323"), Function.identity())
//
//                .exceptionally(e -> {
//                    throw new SystemException("testException");
//                });
//        log.info(stringCompletableFuture.toString());

        log.info("配置获取的结果为{}",testProperties.getAppId());
        log.info("进入消费者");
        try {
            log.info("进入消费者{}", userTestProxy.test("TEXT"));
            if (distributedRedisLock.tryLock("TestLog", 0, 2000, TimeUnit.SECONDS)) {
                log.info("获取分布式锁成功");
                return BaseResponse.buildSuccess(userTestProxy.test("TEXT"));
            }
            log.info("获取分布式锁失败");
            return BaseResponse.buildFail(ExceptionEnum.NO_REPEAT_CLICK);
        } catch (Exception e) {
            log.error("调用dubbo出现异常", e);
            throw new SystemException(e.getMessage());
        } finally {
            distributedRedisLock.unlock("TestLog");
        }
    }
}
