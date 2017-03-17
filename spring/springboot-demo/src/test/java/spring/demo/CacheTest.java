package spring.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import spring.demo.persistence.primary.domain.DemoUser;
import spring.demo.persistence.primary.jpa.IUserRepository;

import javax.annotation.Resource;

/**
 * Created by facheng on 17.03.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTest.class);
    @Resource
    private IUserRepository userRepository;

    @Resource
    private CacheManager cacheManager;

    @Test
    public void testCache() {
        DemoUser u1 = userRepository.findByName("a");
        LOGGER.info("第一次查询");
        DemoUser u2 = userRepository.findByName("a");
        LOGGER.info("第二次查询");
    }
}
