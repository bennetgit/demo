package spring.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import spring.demo.aspect.strategy.ICacheStrategy;
import spring.demo.cache.CacheOperateType;
import spring.demo.cache.Cached;

/**
 * Created by facheng on 17-11-17.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyCache {

    Class<? extends ICacheStrategy> cacheStrategy();

    CacheOperateType operateType() default CacheOperateType.UN_KNOW;

    Class<? extends Cached> cachedType() default Cached.class;
}
