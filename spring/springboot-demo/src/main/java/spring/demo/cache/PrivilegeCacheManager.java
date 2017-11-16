package spring.demo.cache;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by facheng on 17-11-16.
 */
@Component
public class PrivilegeCacheManager {

    @Resource
    private CacheManager cacheManager;
}
