package com.js;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class OperationApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        Semaphore semp = new Semaphore(5);
        semp.tryAcquire();

//        final RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test", StringCodec.INSTANCE);
//        scoredSortedSet.expire(40, TimeUnit.SECONDS);
//        scoredSortedSet.addAndGetRevRank(2, "test3");
//        scoredSortedSet.addAndGetRevRank(3, "test1");
//        scoredSortedSet.addAndGetRevRank(4, "test2");
//        scoredSortedSet.addScore("test1",2);
//        final Integer test1 = scoredSortedSet.revRank("test1") + 1;
//        System.out.println(test1);
//
//
//        RMap<String, String> testhash = redissonClient.getMap("testhash", StringCodec.INSTANCE);
//        final String s = testhash.get("1");
//        final Map<String, Boolean> map = JSONObject.parseObject(s, Map.class);
//        map.forEach((k,v) ->{
//            System.out.println("当前key=" + k + "对应的value=" + v);
//        });
//
//        final RAtomicLong testLong = redissonClient.getAtomicLong("testLong");
//        testLong.getAndAdd(100);
//        final long andAdd = testLong.getAndAdd(200);
//        testLong.addAndGetAsync(-1000);
//        final long l = testLong.get();
//        System.out.println(andAdd + "<---------------->" + l);
        final RLock testLock = redissonClient.getFairLock("testLock");
        try {
            System.out.println(Thread.currentThread().getId());
            for (int i = 0; i < 10; i++) {
                testLock.tryLock(10, TimeUnit.MINUTES);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List list = new LinkedList();
        RLock test1 = redissonClient.getLock("test");
        test1.tryLock();
//        RIdGenerator idGenerator = redissonClient.getIdGenerator("spring-application-name");
//        idGenerator.tryInit(1,18);
//        for (int i = 0; i < 2048; i++) {
//            log.info("{}",idGenerator.getIdleTimeAsync().get());
//        }
//        RKeys keys = redissonClient.getKeys();
//        keys.getKeys().forEach(temp -> {
//            log.info("{}",temp);
//        });
//        Config config = redissonClient.getConfig().setCodec(StringCodec.INSTANCE);
//        System.out.println(config.toYAML());
//        TransactionOptions transactionOptions = TransactionOptions.defaults();
//        RTransaction transaction = redissonClient.createTransaction(transactionOptions);
//        RBatch batch = redissonClient.createBatch();
//        BatchResult<?> execute = batch.execute();
//        List<?> responses = execute.getResponses();
//        RBucket<String> testBucket = redissonClient.getBucket("testBucket");
//        testBucket.set("123");
//        System.out.println(testBucket.get());
        final RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test");
//        scoredSortedSet.expire(40, TimeUnit.SECONDS);
//        scoredSortedSet.addAndGetRevRank(20000000, "test3");
//        scoredSortedSet.addAndGetRevRank(50000000, "test1");
//        scoredSortedSet.addAndGetRevRank(70000000, "test2");
//        final Integer test1 = scoredSortedSet.revRank("test1") + 1;
//        System.out.println(test1);
        RMap<String, String> test = redissonClient.getMap("test", StringCodec.INSTANCE);
        test.put("1111","{12345678987654345}");
        Object o = test.get("1111");
        System.out.println(o.toString());
//        test.clear();
//        RMapCache<String, Object> test1 = redissonClient.getMapCache("test", StringCodec.INSTANCE);
//        test1.put("1111","{12345678987654345}");
//        System.out.println(test1.get("1111"));
//        RKeys keys = redissonClient.getKeys();
//        System.out.println(keys.getKeys());
//        RMapCache<String, Object> map = redissonClient.getMapCache("anyMap");
//        // ttl = 10 minutes,
//        map.put("key1", "new Object()", 10, TimeUnit.MINUTES);
//        // ttl = 10 minutes, maxIdleTime = 10 seconds
//        map.put("key1", "new Object()", 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);
//        // ttl = 3 seconds
//        map.putIfAbsent("key2", "new Object()", 3, TimeUnit.SECONDS);
//        // ttl = 40 seconds, maxIdleTime = 10 seconds
//        map.putIfAbsent("key2", "new Object()", 40, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);

    }

}
