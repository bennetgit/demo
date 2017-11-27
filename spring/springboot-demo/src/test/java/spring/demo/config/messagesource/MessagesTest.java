package spring.demo.config.messagesource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by facheng on 17-11-23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesTest {

    @Test
    public void getMessageTest() {
        String result = Messages.get("test.01");
        System.out.println(result);
    }
}
