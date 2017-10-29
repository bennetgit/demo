package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.persistence.primary.domain.Menu;

/**
 * Created by feng on 17/10/29.
 */
public interface IMenuRepository extends JpaRepository<Menu, Long> {
}
