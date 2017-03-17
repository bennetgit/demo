package spring.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.demo.mq.sender.Sender;

import javax.annotation.Resource;

/**
 * Created by facheng on 17.03.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RabbitMqTest {

    @Resource
    private Sender sender;

    @Test
    public void test() {
        sender.send();
    }
}
