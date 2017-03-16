package spring.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.demo.domain.DemoUser;

/**
 * Created by facheng on 16.03.17.
 */
public interface IUserRepository extends JpaRepository<DemoUser, Long> {

    DemoUser findByName(String name);

    DemoUser findByNameAndAge(String name, Integer age);

    @Query("from DemoUser u where u.name = :name")
    DemoUser findUser(@Param("name") String name);
}
