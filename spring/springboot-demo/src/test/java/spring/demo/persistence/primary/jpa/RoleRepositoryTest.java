package spring.demo.persistence.primary.jpa;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfacheng on 2017-11-03.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Resource
    private IRoleRepository RoleRepository;

    private Role mockRole;

    @Before
    public void setup() {
        RoleRepository.deleteAll();
        List<Menu> mockMenus = new ArrayList<>();
        mockRole = new Role();
        mockRole.setName("role_1");
        mockRole.setMenus(mockMenus);
    }

    @Test
    public void saveMenuTest() {
        RoleRepository.save(mockRole);
    }
}
