package spring.demo.persistence.primary.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.demo.enums.SexType;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.domain.User;

/**
 * Created by wangfacheng on 2017-11-03.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private IUserRepository userRepository;

    private User mockUser;

    @Before
    public void setup() {
        userRepository.deleteAll();

        List<Menu> mockMenus = new ArrayList<>();

        List<Role> roles = new ArrayList<Role>() {
            {
                Role role = new Role();
                role.setName("role_1");
                role.setMenus(mockMenus);
            }
        };

        mockUser = new User();
        mockUser.setMobile("11");
        mockUser.setPassword("12312321");
        mockUser.setSex(SexType.FEMALE);
        mockUser.setUsername("hello world");
        mockUser.setRoles(roles);

    }

    @Test
    public void saveUserTest() {
        userRepository.save(mockUser);
    }
}
