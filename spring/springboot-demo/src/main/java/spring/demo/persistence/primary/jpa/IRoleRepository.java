package spring.demo.persistence.primary.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.demo.persistence.common.MyJpaWithPageRepository;
import spring.demo.persistence.primary.domain.Role;

/**
 * Created by wangfacheng on 2017-11-03.
 */

public interface IRoleRepository extends MyJpaWithPageRepository<Role> {

    @Query("from Role r inner join r.users users where users.id =:userId ")
    List<Role> findRolesWithUserId(@Param("userId") Long userId);

    @Query("from Role order by id")
    List<Role> findAllOrderById();

    @Query("from Role r where r.id in :ids")
    List<Role> findRoleByIds(@Param("ids") List<Long> ids);

    @Query("from Role")
    List<Role> findAll();

}
