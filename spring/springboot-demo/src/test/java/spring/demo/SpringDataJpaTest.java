package spring.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IUserRepository;

/**
 * Created by facheng on 16.03.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataJpaTest {

    @Autowired
    private IUserRepository userRepository;

    @Before
    public void doBefore() {
        userRepository.deleteAll();
    }

    @Test
    public void testSpringDataJpa() {
        userRepository.save(new User("a", "4"));
        userRepository.save(new User("b", "4"));
        userRepository.save(new User("c", "4"));
        userRepository.save(new User("d", "4"));
        userRepository.save(new User("e", "4"));

        Assert.assertEquals(5, userRepository.count());

    }
}
