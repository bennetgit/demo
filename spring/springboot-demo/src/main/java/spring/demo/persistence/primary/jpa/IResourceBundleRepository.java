package spring.demo.persistence.primary.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.demo.persistence.primary.domain.ResourceBundle;

/**
 * Created by facheng on 17-11-27.
 */

public interface IResourceBundleRepository extends JpaRepository<ResourceBundle, Long> {
}
