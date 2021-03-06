package spring.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IUserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * Created by wangfacheng on 2017-11-08.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private IUserService userService;

    @Resource
    private EntityManager em;

    @Resource
    private IUserRepository userRepository;

    @Test
    public void createTest() {
    }

    @Test
    public void getUserByUsernameTest() {

        // User user = em.find(User.class, 12550l);

        User user = userRepository.findUser("hello");

        System.out.println(user.getRoles());

        // UserDto userDto = userService.getUserByName("admin");
    }
}
