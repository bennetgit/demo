package spring.demo.cache;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import spring.demo.constant.Constants;
import spring.demo.exception.UnSupportCacheException;
import spring.demo.util.MyCacheUtils;
import spring.demo.util.SpringContextHolder;

/**
 * Created by facheng on 17-11-16.
 */

@Component(Constants.CacheConfig.CACHE_KEY_GENERATOR)
public class CacheKeyGenerator implements KeyGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheKeyGenerator.class);

    @Override
    public Object generate(Object target, Method method, Object... params) {
        LOGGER.info("start generate cache key, target {}, method {}, params {}", target, method, params);
        if (params == null) {
            throw new UnSupportCacheException();
        }

        boolean isCached = Arrays.asList(params).stream().anyMatch(param -> param instanceof Cached);
        if (!isCached) {
            throw new UnSupportCacheException();
        }

        Arrays.asList(params).stream().forEach(param -> {

            if (param instanceof Cached) {
                ((MyKeyGenerator) SpringContextHolder.getBean(MyCacheUtils.getLowerCaseByClass(param.getClass())
                        + Constants.CacheConfig.KEY_GENERATOR_SUFFIX)).generate((Cached) param);
                return;

            }
        });

        return null;
    }
}
