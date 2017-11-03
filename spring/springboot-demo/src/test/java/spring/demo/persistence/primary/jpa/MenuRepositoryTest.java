package spring.demo.persistence.primary.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.demo.persistence.primary.domain.Menu;

import javax.annotation.Resource;

/**
 * Created by wangfacheng on 2017-11-03.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRepositoryTest {

    @Resource
    private IMenuRepository menuRepository;

    private Menu mockMenu;

    @Before
    public void setup() {
        menuRepository.deleteAll();

        mockMenu = new Menu();
        mockMenu.setName("menu1");
        mockMenu.setSequence(1);
        mockMenu.setUrl("index.index");
    }

    @Test
    public void saveMenuTest() {
        menuRepository.save(mockMenu);
    }
}
