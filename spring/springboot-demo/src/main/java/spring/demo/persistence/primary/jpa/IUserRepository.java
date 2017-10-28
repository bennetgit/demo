package spring.demo.persistence.primary.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.demo.persistence.primary.domain.User;

/**
 * Created by facheng on 16.03.17.
 */

@CacheConfig(cacheNames = "demoUsers")
public interface IUserRepository extends JpaRepository<User, Long> {

    //#p0 --> SpEL表达式 #p0即是第一个参数
    @Cacheable(key = "#p0")
    User findByUsername(String name);

    User findByUsernameAndAge(String username, Integer age);

    @Query("from User u where u.username = :name")
    User findUser(@Param("name") String name);

    @Cacheable(key = "#p0.username")
    User save(User user);
}
