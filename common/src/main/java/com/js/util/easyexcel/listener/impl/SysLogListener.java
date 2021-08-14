package com.js.util.easyexcel.listener.impl;

import com.js.util.easyexcel.listener.ExcelListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
public class SysLogListener<T> extends ExcelListener<T> {

    @Override
    public void doSomething(List<T> object) {
        // 通过类名获取Bean 举个例子
//        TestController bean = SpringUtil.getBean(TestController.class);

        // 写数据库，类型转换,生成id等操作
        log.info("{}", object);
    }
}