package spring.demo.persistence.primary.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.demo.persistence.primary.domain.DemoUser;

/**
 * Created by facheng on 16.03.17.
 */

@CacheConfig(cacheNames = "demoUsers")
public interface IUserRepository extends JpaRepository<DemoUser, Long> {

    //#p0 --> SpEL表达式 #p0即是第一个参数
    @Cacheable(key = "#p0")
    DemoUser findByName(String name);

    DemoUser findByNameAndAge(String name, Integer age);

    @Query("from DemoUser u where u.name = :name")
    DemoUser findUser(@Param("name") String name);

    @Cacheable(key = "#p0.name")
    DemoUser save(DemoUser user);
}
