package com.js;

import com.alibaba.fastjson2.JSONObject;
import com.js.distributed.DistributedRedisLock;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.BatchResult;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RTransaction;
import org.redisson.api.RedissonClient;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
//@ActiveProfiles("test")
class OperationApplicationTests {

    @Autowired
    private RedissonClient redissonClient;
    @Resource(name = "commonThreadPool")
    private ExecutorService commonThreadPool;
    @Resource
    private DistributedRedisLock distributedRedisLock;

    @Test
    void testRAtomicLong(){
        final RAtomicLong testLong = redissonClient.getAtomicLong("testLong");
        testLong.getAndAdd(100);
        final long andAdd = testLong.getAndAdd(200);
        testLong.addAndGetAsync(-1000);
        final long l = testLong.get();
        System.out.println(andAdd + "<---------------->" + l);
        final RLock testLock = redissonClient.getFairLock("testLock");
        try {
            System.out.println(Thread.currentThread().getId());
            for (int i = 0; i < 10; i++) {
                testLock.tryLock(10, TimeUnit.MINUTES);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testBucket() {

        RBucket<String> testBucket = redissonClient.getBucket("testBucket");
        testBucket.set("123");
        System.out.println(testBucket.get());
    }

    @Test
    void testTran() throws Exception {
        Config config = redissonClient.getConfig().setCodec(StringCodec.INSTANCE);
        System.out.println(config.toYAML());
        TransactionOptions transactionOptions = TransactionOptions.defaults();
        RTransaction transaction = redissonClient.createTransaction(transactionOptions);
        RBatch batch = redissonClient.createBatch();
        BatchResult<?> execute = batch.execute();
        List<?> responses = execute.getResponses();
        System.out.println(responses);
        transaction.commitAsync();
    }

    @Test
    void testGenId() throws Exception {
        RIdGenerator idGenerator = redissonClient.getIdGenerator("spring-application-name");
        idGenerator.tryInit(1, 18);
        for (int i = 0; i < 2048; i++) {
            log.info("{}", idGenerator.getIdleTimeAsync().get());
        }
        RKeys keys = redissonClient.getKeys();
        keys.getKeys().forEach(temp -> log.info("{}", temp));
    }

    @Test
    void testMapcahce() {
        RMapCache<String, Object> test1 = redissonClient.getMapCache("testCahce", StringCodec.INSTANCE);
        test1.put("1111", "{12345678987654345}");
        System.out.println(test1.get("1111"));
        RKeys keys = redissonClient.getKeys();
        System.out.println(keys.getKeys());
    }

    @Test
    void testMap() {
        RMapCache<String, Object> map = redissonClient.getMapCache("anyMap");
        // ttl = 10 minutes,
        map.put("key1", "new Object()", 10, TimeUnit.MINUTES);
        // ttl = 10 minutes, maxIdleTime = 10 seconds
        map.put("key1", "new Object()", 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);
        // ttl = 3 seconds
        map.putIfAbsent("key2", "new Object()", 3, TimeUnit.SECONDS);
        // ttl = 40 seconds, maxIdleTime = 10 seconds
        map.putIfAbsent("key2", "new Object()", 40, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);
    }

    @Test
    void testHash() {
        RMap<String, String> testhash = redissonClient.getMap("testhash", StringCodec.INSTANCE);
        final String s = testhash.get("1");
        final Map<String, Boolean> map = JSONObject.parseObject(s, Map.class);
        map.forEach((k, v) -> {
            System.out.println("当前key=" + k + "对应的value=" + v);
        });
    }

    @Test
    void testZset() {
        final RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test", StringCodec.INSTANCE);
        scoredSortedSet.addAndGetRevRank(2, "test3");
        scoredSortedSet.addAndGetRevRank(3, "test1");
        scoredSortedSet.addAndGetRevRank(4, "test2");
        scoredSortedSet.addScore("test1", 2);
        final Integer test1 = scoredSortedSet.revRank("test1") + 1;
        System.out.println(test1);
    }

    @Test
    void testRedssion() {
        Boolean lock = distributedRedisLock.tryLock("testLock", 100, 5, TimeUnit.SECONDS);
        if (!lock) {
            throw new RuntimeException("获取分布式锁失败");
        }
        try {
            System.out.println("100000000000");
        } catch (Exception e) {
            System.out.println("222222222222");
        } finally {
            distributedRedisLock.unlock("testLock");
        }
    }

    @Test
    void testCallable() throws Exception {

        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Callable c = () -> {
                System.out.println(finalI + "->线程执行");
                return finalI;
            };
            Future f = commonThreadPool.submit(c);
            list.add(f);
        }
        commonThreadPool.shutdown();
        for (Future future : list) {
            System.out.println("res =======" + future.get().toString());
        }

    }

}
