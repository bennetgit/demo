package spring.demo.service.common;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import spring.demo.constant.Constants;

/**
 * Created by feng on 17/11/18.
 */

@CacheConfig(cacheNames = Constants.CacheConfig.CACHE_NAME, keyGenerator = Constants.CacheConfig.CACHE_KEY_GENERATOR)
public interface CachedService<T, R> {

    @CachePut(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    R saveOrUpdateWithCache(T dto);

    @CacheEvict(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    void deleteWithCache(T dto);

}
