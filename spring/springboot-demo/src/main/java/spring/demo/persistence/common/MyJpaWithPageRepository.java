package spring.demo.persistence.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by feng on 17/11/18.
 */

public interface MyJpaWithPageRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
