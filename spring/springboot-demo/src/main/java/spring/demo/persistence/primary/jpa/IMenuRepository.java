package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.demo.persistence.primary.domain.Menu;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public interface IMenuRepository extends JpaRepository<Menu, Long> {
}
