package com.js.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: NacosMaven
 * @Date: 2020/9/2 16:26
 * @Author: jiangshuang
 * @Description:
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String getString (){
        return "test";
    }
}
