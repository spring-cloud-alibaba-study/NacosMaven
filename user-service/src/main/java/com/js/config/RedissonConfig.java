package com.js.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author
 * @date 2019-07-26
 */
@Configuration
public class RedissonConfig {
    @Bean(destroyMethod="shutdown",value = "redissonClient")
    public RedissonClient redissonClient() throws IOException {
        RedissonClient redisson = Redisson.create(
                Config.fromYAML(new ClassPathResource("application-redisson.yml").getInputStream()));
        return redisson;
    }

}