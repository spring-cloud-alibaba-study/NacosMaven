package com.js.controller;

import com.js.distributed.DistributedRedisLock;
import com.js.enums.ExceptionEnum;
import com.js.exception.SystemException;
import com.js.feignclient.UserTestProxy;
import com.js.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "测试Controller")
public class TestController {

    @Autowired
    private DistributedRedisLock distributedRedisLock;

    @Autowired
    private UserTestProxy userTestProxy;

    @GetMapping("/test")
    @ApiOperation("test方法")
    public BaseResponse<String> getString() {
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
