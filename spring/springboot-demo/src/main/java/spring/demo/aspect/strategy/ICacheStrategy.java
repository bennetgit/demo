package spring.demo.aspect.strategy;

import spring.demo.cache.CacheParam;

/**
 * Created by facheng on 17-11-16.
 */
public interface ICacheStrategy {

    Object execute(CacheParam cacheParam);
}
