package com.js.util;

import com.js.util.NetUtils;
import com.js.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class SnowFlakeUtil {
    private static volatile SnowFlake instance;

    /**
     * 数据中心ID
     **/
    private static volatile long datacenterId;

    /**
     * 机器ID
     **/
    private static volatile long machineId;

    public static SnowFlake getInstance() {
        if (instance == null) {
            synchronized (SnowFlake.class) {
                if (instance == null) {
                    initManyId();
                    log.info("获取雪花算法工具包为空，开始初始化雪花算法工具包数据中心id={},机器id={}", datacenterId, machineId);
                    instance = new SnowFlake(machineId, datacenterId);
                }
            }
        }
        return instance;
    }

    private static void initManyId() {
        datacenterId = Long.valueOf(NetUtils.getThreeIp(NetUtils.getLocalIp()));
        machineId = Long.valueOf(NetUtils.getLastIp(NetUtils.getLocalIp()));
    }
}