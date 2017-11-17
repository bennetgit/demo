package spring.demo.aspect.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spring.demo.cache.CacheParam;

/**
 * Created by feng on 17/11/18.
 */
public abstract class AbstractCacheStrategy implements ICacheStrategy {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractCacheStrategy.class);

    @Override
    public Object execute(CacheParam cacheParam) {
        LOGGER.info("execute cacheStrategy {}", cacheParam);
        if (cacheParam == null || cacheParam.getCacheOperateType() == null) {
            return null;
        }

        switch (cacheParam.getCacheOperateType()) {
        case CREATE:
            return create(cacheParam);
        case DELETE:
            return delete(cacheParam);
        case UPDATE:
            return update(cacheParam);
        case FIND:
            return find(cacheParam);
        default:
            break;
        }
        return null;

    }

    protected Object create(CacheParam cacheParam) {
        return null;
    }

    protected Object delete(CacheParam cacheParam) {
        return null;
    }

    protected Object update(CacheParam cacheParam) {
        return null;
    }

    protected Object find(CacheParam cacheParam) {
        return null;
    }
}
