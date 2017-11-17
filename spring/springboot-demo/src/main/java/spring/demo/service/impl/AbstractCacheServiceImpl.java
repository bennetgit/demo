package spring.demo.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import spring.demo.service.ICacheService;

/**
 * Created by feng on 17/11/17.
 */
public abstract class AbstractCacheServiceImpl implements ICacheService, InitializingBean {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractCacheServiceImpl.class);

    @Resource
    protected CacheManager cacheManager;

    protected Cache cache;

    protected abstract String getCacheName();

    @Override
    public Object get(Object key) {
        LOGGER.info("get value from cache {} key {}", getCacheName(), key);
        return cache.get(key);
    }

    @Override
    public Object add(Object key, Object value) {
        LOGGER.info("add value from cache {} key {} value {}", getCacheName(), key, value);
        cache.put(key, value);
        return value;
    }

    @Override
    public void delete(Object key) {
        LOGGER.info("delete value from cache {} key {}", getCacheName(), key);
        cache.evict(key);
    }

    @Override
    public Object update(Object key, Object value) {
        LOGGER.info("update value from cache {} key {} value {}", getCacheName(), key, value);
        cache.put(key, value);
        return value;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("{} init start...", getCacheName());

        cache = cacheManager.getCache(getCacheName());
        long start = System.currentTimeMillis();
        initCache();
        LOGGER.info("{} init end, cost {}ms ", getCacheName(), System.currentTimeMillis() - start);

    }

    protected void initCache() {
    }
}
