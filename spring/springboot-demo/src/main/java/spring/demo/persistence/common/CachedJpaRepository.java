package spring.demo.persistence.common;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.constant.Constants;

/**
 * Created by feng on 17/11/18.
 */

@CacheConfig(cacheNames = Constants.CacheConfig.CACHE_NAME, keyGenerator = Constants.CacheConfig.CACHE_KEY_GENERATOR)
public interface CachedJpaRepository<T> extends JpaRepository<T, Long> {

    @CachePut(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    <S extends T> S save(S entity);

    @CacheEvict(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    void delete(T role);

}
