package com.hong610.common.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Async 异步方法
 * Created by Hong on 2016/11/28.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${thread.queue.capacity}")
    private Integer queueCapacity;
    @Value("${thread.core.pool.size}")
    private Integer corePoolSize;
    @Value("${thread.max.pool.size}")
    private Integer maxPoolSize;
    @Value("${thread.keep.alive.seconds}")
    private Integer keepAliveSeconds;

    /**
     * 自定义线程池
     */
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池所使用的缓冲队列  
        poolTaskExecutor.setQueueCapacity(200);  
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(5);  
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(1000);  
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        return poolTaskExecutor;
    }
}
