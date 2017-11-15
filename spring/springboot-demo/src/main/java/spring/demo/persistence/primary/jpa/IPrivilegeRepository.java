package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import spring.demo.persistence.primary.domain.Privilege;

/**
 * Created by feng on 17/11/14.
 */

public interface IPrivilegeRepository extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege> {
}
