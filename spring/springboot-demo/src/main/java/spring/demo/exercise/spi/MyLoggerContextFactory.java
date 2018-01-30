package spring.demo.exercise.spi;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

import java.net.URI;

/**
 * Created by wangfacheng on 2018-01-30.
 */
public class MyLoggerContextFactory implements LoggerContextFactory {

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext) {
        System.out.println("============ get context1 ==========");
        return null;
    }

    @Override
    public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext,
            URI configLocation, String name) {
        System.out.println("============ get context1 ==========");
        return null;
    }

    @Override
    public void removeContext(LoggerContext context) {
        System.out.println("============ remove context1 ==========");
    }
}
