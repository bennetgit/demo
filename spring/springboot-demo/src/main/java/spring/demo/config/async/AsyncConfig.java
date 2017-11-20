package spring.demo.config.async;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by wangfacheng on 2017-11-14.
 */

@Configuration
public class AsyncConfig {

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
        // 线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(corePoolSize);
        // 线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        // 线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        return poolTaskExecutor;
    }

}
