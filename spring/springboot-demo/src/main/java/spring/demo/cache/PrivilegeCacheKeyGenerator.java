package spring.demo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import spring.demo.constant.Constants;
import spring.demo.persistence.primary.domain.Privilege;

import java.lang.reflect.Method;

/**
 * Created by facheng on 17-11-16.
 */

@Component
public class PrivilegeCacheKeyGenerator implements MyKeyGenerator<Privilege> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeCacheKeyGenerator.class);

    @Override
    public Object generate(Privilege cache) {
        LOGGER.info("xxxxxx {}", cache.getName());
        return null;
    }
}
