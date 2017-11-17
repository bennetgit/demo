package spring.demo.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import spring.demo.service.ICacheService;

/**
 * Created by facheng on 17-11-17.
 */

@Service
public class CacheServiceImpl implements ICacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Resource
    private CacheManager cacheManager;

    @Override
    public Object get(Object key) {
        LOGGER.info("" + key);
        return null;
    }
}
