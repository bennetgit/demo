package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.demo.persistence.primary.domain.LoginAudit;

/**
 * Created by feng on 17/11/14.
 */

public interface ILoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}
