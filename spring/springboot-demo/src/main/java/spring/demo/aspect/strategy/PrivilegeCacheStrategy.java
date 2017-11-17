package spring.demo.aspect.strategy;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import spring.demo.cache.CacheParam;
import spring.demo.service.ICacheService;

/**
 * Created by facheng on 17-11-16.
 */
@Component
public class PrivilegeCacheStrategy implements ICacheStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeCacheStrategy.class);

    @Resource
    private ICacheService cacheService;

    @Override
    public void execute(CacheParam cacheParam) {
        LOGGER.info("execute PrivilegeCacheStrategy {}", cacheParam);

        cacheService.get("hello world");
    }
}
