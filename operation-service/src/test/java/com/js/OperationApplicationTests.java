package com.js;

import org.junit.jupiter.api.Test;
import org.redisson.api.RScoredSortedSet;
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
        final RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet("test");
        scoredSortedSet.expire(40, TimeUnit.SECONDS);
        scoredSortedSet.addAndGetRevRank(20000000, "test3");
        scoredSortedSet.addAndGetRevRank(50000000, "test1");
        scoredSortedSet.addAndGetRevRank(70000000, "test2");
        final Integer test1 = scoredSortedSet.revRank("test1") + 1;
        System.out.println(test1);
    }

}
