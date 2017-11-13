package spring.demo.persistence.primary.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.demo.persistence.primary.domain.Role;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("from Role r inner join r.users users where users.id =:userId ")
    List<Role> findRolesWithUserId(@Param("userId") Long userId);

    @Query("from Role order by id")
    List<Role> findAllOrderById();

    @Query("from Role r where r.id in :ids")
    List<Role> findRoleByIds(@Param("ids") List<Long> ids);

}
