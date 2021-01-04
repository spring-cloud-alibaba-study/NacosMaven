package com.js.dubbo;


import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@Service
public class SayHelloServiceImpl implements TestDubboService {

    @Override
    public String sayHello(String msg) {
        return "Hello, GuPaoEdu.cn";
    }
}
