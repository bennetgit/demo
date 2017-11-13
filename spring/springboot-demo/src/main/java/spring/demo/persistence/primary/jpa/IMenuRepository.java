package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.demo.persistence.primary.domain.Menu;

import java.util.List;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public interface IMenuRepository extends JpaRepository<Menu, Long> {

    @Query("from Menu m where m.id in :ids")
    List<Menu> findMenuWithIds(@Param("ids") List<Long> menuIds);
}
