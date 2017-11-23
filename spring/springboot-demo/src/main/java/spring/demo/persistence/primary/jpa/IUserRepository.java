package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.demo.persistence.common.MyJpaWithPageRepository;
import spring.demo.persistence.primary.domain.User;

/**
 * Created by facheng on 16.03.17.
 */

public interface IUserRepository extends MyJpaWithPageRepository<User> {

    @Query("from User u where u.username = :name and u.status = 1")
    User findUser(@Param("name") String name);

    User save(User user);

    User findById(Long id);
}
