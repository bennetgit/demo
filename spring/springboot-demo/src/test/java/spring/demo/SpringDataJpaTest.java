package spring.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.demo.dao.IUserRepository;
import spring.demo.domain.DemoUser;

import javax.annotation.Resource;

/**
 * Created by facheng on 16.03.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataJpaTest {

    @Resource
    private IUserRepository userRepository;

    @Before
    public void doBefore() {
        userRepository.deleteAll();
    }

    @Test
    public void testSpringDataJpa() {
        userRepository.save(new DemoUser("a", 1));
        userRepository.save(new DemoUser("b", 2));
        userRepository.save(new DemoUser("c", 3));
        userRepository.save(new DemoUser("d", 4));
        userRepository.save(new DemoUser("e", 5));

        Assert.assertEquals(5, userRepository.count());

    }
}
