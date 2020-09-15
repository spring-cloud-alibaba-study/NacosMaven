package com.js.dubbo;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/

public class SayHelloServiceImpl implements TestDubboService{

    @Override
    public String sayHello(String msg) {
        return "Hello, GuPaoEdu.cn";
    }
}
