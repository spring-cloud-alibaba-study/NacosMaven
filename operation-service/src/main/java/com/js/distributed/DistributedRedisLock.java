package com.js.distributed;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式Redis锁
 */
@Slf4j
@Component
public class DistributedRedisLock {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @return
     * @Description: 上锁
     * @Param [key, waitTime, leaseTime, unit]
     * @Date: 2021/7/20 9:48 AM
     */
    public Boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
            return false;
        }

        try {
            RLock lock = redissonClient.getLock(key);
            log.info("当前锁的状态为{}", lock.isLocked());
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            log.error("获取分布式锁失败{}", key, e);
            return false;
        }
    }

    /**
     * @return
     * @Description: 释放锁
     * @Param [key]ø
     * @Date: 2021/7/20 9:48 AM
     */
    public void unlock(String key) {
        if (redissonClient == null) {
            log.info("DistributedRedisLock redissonClient is null");
        }

        try {
            RLock lock = redissonClient.getLock(key);
            if (lock.isLocked()) {
                lock.unlock();
            }
        } catch (Exception e) {
            log.error("分布式锁释放失败{}", key, e);
        }
    }

}
