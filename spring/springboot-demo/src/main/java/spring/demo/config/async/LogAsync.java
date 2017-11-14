package spring.demo.config.async;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * Created by wangfacheng on 2017-11-14.
 */

@Component
public class LogAsync {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAsync.class);

    @Async("threadPoolTaskExecutor")
    public Future<String> addLoginLog() {
        LOGGER.info("start async to execute task");
        return new AsyncResult<>("success to add login log info");
    }
}
