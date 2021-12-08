package com.js.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TestControllerApi {

    @GetMapping("/getString")
    String test(@RequestParam("msg") String msg);
}
