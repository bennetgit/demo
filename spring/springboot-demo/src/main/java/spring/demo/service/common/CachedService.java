package spring.demo.service.common;

/**
 * Created by feng on 17/11/18.
 */

public interface CachedService<T, R> {

    R saveOrUpdateWithCache(T dto);

    void deleteWithCache(T dto);

}
