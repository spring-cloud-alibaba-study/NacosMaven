package com.js.controller;

import com.js.enums.ExceptionEnum;
import com.js.exception.SystemException;
import com.js.feignclient.UserTestProxy;
import com.js.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/**
 * @program: NacosMaven
 * @Date: 2020/9/2 16:26
 * @Author: jiangshuang
 * @Description:
 */
@RestController
@Slf4j
@Api(tags = "测试Controller")
public class TestController {

//    @Resource
//    private DistributedRedisLock distributedRedisLock;

    @Autowired
    @Qualifier(value = "userTestProxy")
    private UserTestProxy userTestProxy;

    @Autowired
    private ExecutorService commonThreadPool;

    @GetMapping("/test")
    @ApiOperation("test方法")
    public BaseResponse<String> getString() {

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
                    System.out.println("1000000");
                    return "test";
                }, commonThreadPool).thenComposeAsync(a -> CompletableFuture.supplyAsync(() -> {
                    System.out.println(123);
                    return a + "132";
                }, commonThreadPool))
                .applyToEither(CompletableFuture.supplyAsync(() -> "test2323"), Function.identity())

                .exceptionally(e -> {
                    throw new SystemException("testException");
                });

        log.info(stringCompletableFuture.toString());
        try {
            log.info("进入消费者{}", userTestProxy.test("TEXT"));
//            if (distributedRedisLock.tryLock("TestLog", 0, 2000, TimeUnit.SECONDS)) {
//                log.info("获取分布式锁成功");
//                return BaseResponse.buildSuccess(userTestProxy.test("TEXT"));
//            }
            log.info("获取分布式锁失败");
            return BaseResponse.buildFail(ExceptionEnum.NO_REPEAT_CLICK);
        } catch (Exception e) {
            log.error("调用dubbo出现异常", e);
            throw new SystemException(e.getMessage());
        } finally {
//            distributedRedisLock.unlock("TestLog");
        }
    }
}
