package spring.demo.persistence.primary.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.demo.persistence.common.MyJpaWithPageRepository;
import spring.demo.persistence.primary.domain.Privilege;

/**
 * Created by feng on 17/11/14.
 */

public interface IPrivilegeRepository extends MyJpaWithPageRepository<Privilege> {

    @Query("select p from Privilege p")
    List<Privilege> findAll();

    @Query("select p from Privilege p inner join p.roles pr where pr.id = :roleId")
    List<Privilege> findPrivilegeWithRoleId(@Param("roleId") Long roleId);

    @Query("select p.url from Privilege p inner join p.roles pr where pr.id = :roleId")
    Set<String> findPrivilegeUrlsWithRoleId(@Param("roleId") Long roleId);

}
