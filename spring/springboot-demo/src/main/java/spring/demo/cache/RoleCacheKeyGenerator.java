package spring.demo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import spring.demo.persistence.primary.domain.Role;

/**
 * Created by facheng on 17-11-16.
 */

@Component
public class RoleCacheKeyGenerator implements MyKeyGenerator<Role> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleCacheKeyGenerator.class);

    @Override
    public Object generate(Role cache) {
        LOGGER.info("xxxxxx {}", cache.getName());
        return null;
    }
}
