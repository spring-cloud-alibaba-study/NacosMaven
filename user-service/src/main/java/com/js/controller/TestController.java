package com.js.controller;

import com.js.api.TestControllerApi;

public class TestController implements TestControllerApi {
    @Override
    public String test(String msg) {
        return "YES THIS iS USER_SERVICE";
    }
}
