package com.js.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池
 */
@Configuration
public class TreadPoolConfiguration {

    /**
     * @author: 渡劫 dujie
     * @Description: 通用部分
     * @Date: 8:45 AM 2020/11/20
     * @Param:
     * @return:
     **/
    @Bean(value = "commonThreadPool", destroyMethod = "shutdown")
    public ExecutorService commonExecutorService() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("common-thread-%d").build();
        return new ThreadPoolExecutor(5, 8, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10000),
                namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}