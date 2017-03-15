package spring.demo.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static spring.demo.constant.Constants.*;

/**
 * Created by facheng on 15.03.17.
 */
public final class ExecutorFactory {

    public static ThreadPoolExecutor generateExecutor(int corePoolSize) {

        return new ThreadPoolExecutor(corePoolSize, corePoolSize, DEFAULT_KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new BasicThreadFactory.Builder().daemon(true).namingPattern(DEFAULT_EXECUTOR_NAME_PATTERN).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }

}
