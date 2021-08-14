package com.js.util.snow;

import com.js.util.net.NetUtils;

import java.util.logging.Logger;

public class SnowFlakeUtil {
    private static Logger logger = Logger.getLogger("SnowFlakeUtil");

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
                    logger.info("获取雪花算法工具包为空，开始初始化雪花算法工具包数据中心id=" + datacenterId + ",机器id=" + machineId);
                    instance = new SnowFlake(machineId, datacenterId);
                }
            }
        }
        return instance;
    }

    private static void initManyId() {
        datacenterId = Long.valueOf(String.valueOf(Integer.valueOf(NetUtils.getThreeIp(NetUtils.getLocalIp())) % 32));
        machineId = Long.valueOf(String.valueOf(Integer.valueOf(NetUtils.getLastIp(NetUtils.getLocalIp())) % 32));
    }
}