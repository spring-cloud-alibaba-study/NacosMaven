package com.js.config;

import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2019-07-26
 */
@Configuration
public class RedissonConfig {
    private static final String REDIS_PRE = "redis://";

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient redissonClient() {
        if (StringUtils.isBlank(host) || StringUtils.isBlank(port)){
            throw new RuntimeException("请配置Spring的redis环境");
        }
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_PRE + host + ":" + port);

        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }

}