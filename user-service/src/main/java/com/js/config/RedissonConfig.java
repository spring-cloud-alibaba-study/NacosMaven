package com.js.config;

import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author
 * @date 2019-07-26
 */
@Configuration
public class RedissonConfig {
    private static final String REDIS_PRE = "redis://";

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        if (StringUtils.isBlank(redisProperties.getHost()) || Objects.isNull(redisProperties.getPort())) {
            throw new RuntimeException("请配置Spring的redis环境");
        }
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_PRE + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setPassword(StringUtils.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());

        System.out.println(redisProperties.getPassword());
        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }

}