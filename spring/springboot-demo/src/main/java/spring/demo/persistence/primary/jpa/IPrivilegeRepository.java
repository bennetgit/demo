package spring.demo.persistence.primary.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.demo.constant.Constants;
import spring.demo.persistence.primary.domain.Privilege;

import java.util.List;

/**
 * Created by feng on 17/11/14.
 */

@CacheConfig(cacheNames = Constants.CacheConfig.CACHE_NAME_PRIVILEGE, keyGenerator = Constants.CacheConfig.PRIVILEGE_CACHE_KEY_GENERATOR)
public interface IPrivilegeRepository extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege> {

    @Query("select p from Privilege p")
    List<Privilege> findAll();

    @Query("select p from Privilege p inner join p.roles pr where pr.id = :roleId")
    List<Privilege> findPrivilegeWithRoleId(@Param("roleId") Long roleId);

    @CachePut
    Privilege save(Privilege privilege);

    @CacheEvict
    void delete(Privilege privilege);
}
