package spring.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import spring.demo.annotation.MyCache;
import spring.demo.aspect.strategy.ICacheStrategy;
import spring.demo.cache.CacheParam;
import spring.demo.constant.Constants;
import spring.demo.util.MyCacheUtils;
import spring.demo.util.SpringContextHolder;

/**
 * Created by facheng on 17-11-16.
 */
@Aspect
@Order(Constants.DEFAULT_AOP_ORDER_AFTER_CACHE)
public class MyCacheHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCacheHandler.class);

    @Pointcut("@annotation(spring.demo.annotation.MyCache)")
    public void pointCut() {
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        MyCache myCache = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(MyCache.class);

        Class clazz;
        if (myCache == null || (clazz = myCache.cacheStrategy()) == null) {
            return;
        }

        ICacheStrategy cacheStrategy = (ICacheStrategy) SpringContextHolder
                .getBean(MyCacheUtils.getLowerCaseByClass(clazz));

        if (cacheStrategy == null) {
            return;
        }

        cacheStrategy.execute(CacheParam.of(joinPoint, myCache.operateType()));

    }

}
