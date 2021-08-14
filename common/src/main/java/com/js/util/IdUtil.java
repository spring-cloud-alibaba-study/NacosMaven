package com.js.util;

import com.js.util.snow.SnowFlakeUtil;

import java.util.UUID;

/**
 * @Author dujie 分布式id生成器
 */
public class IdUtil {

    private IdUtil() {
        throw new IllegalStateException("IdUtils工具异常");
    }

    public static synchronized String get32Uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static synchronized String getUuid() {

        return String.valueOf(SnowFlakeUtil.getInstance().nextId()).replace("-", "");
    }

    public static synchronized Long getLongId() {
        return SnowFlakeUtil.getInstance().nextId();
    }
}
