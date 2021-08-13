package com.js.controller;

import com.js.dubbo.TestDubboService;
import com.js.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public BaseResponse<String> getString() {
        log.info("进入消费者");
        return BaseResponse.buildSuccess(testDubboService.sayHello("jiangshuang"));
    }
}
