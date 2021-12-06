package com.js.controller;

import com.js.api.TestControllerApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController implements TestControllerApi {

    @Override
    public String test(@RequestParam("msg") String msg) {
        log.info("请求进入");
        return "YES THIS iS USER_SERVICE";
    }
}
