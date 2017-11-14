package spring.demo.persistence.primary.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.demo.persistence.primary.domain.User;

/**
 * Created by facheng on 16.03.17.
 */

@CacheConfig(cacheNames = "demoUsers")
public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("from User u where u.username = :name and u.status = 1")
    User findUser(@Param("name") String name);

    // @Cacheable(key = "#p0.username")
    // #p0 --> SpEL表达式 #p0即是第一个参数
    User save(User user);

    User findById(Long id);
}
