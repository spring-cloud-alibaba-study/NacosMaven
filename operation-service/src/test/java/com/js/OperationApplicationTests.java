package com.js;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class OperationApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {

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
    }

}
