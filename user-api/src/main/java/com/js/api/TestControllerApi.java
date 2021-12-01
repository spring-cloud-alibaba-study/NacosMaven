package com.js.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/user/test")
public interface TestControllerApi {

    @GetMapping("/getString")
    String test(@RequestParam("msg") String msg);
}
