package spring.demo.aspect.strategy;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.demo.cache.CacheParam;
import spring.demo.service.ICacheService;

/**
 * Created by facheng on 17-11-16.
 */
@Component
public class PrivilegeCacheStrategy extends AbstractCacheStrategy {

    @Resource
    @Qualifier("privilegeCacheServiceImpl")
    private ICacheService cacheService;

    @Override
    protected Object update(CacheParam cacheParam) {
        // cacheService.update();
        return super.update(cacheParam);
    }

    @Override
    protected Object delete(CacheParam cacheParam) {
        return super.delete(cacheParam);
    }

    @Override
    protected Object create(CacheParam cacheParam) {
        return super.create(cacheParam);
    }
}
