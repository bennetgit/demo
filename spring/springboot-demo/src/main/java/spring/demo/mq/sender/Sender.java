package spring.demo.mq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;

import static spring.demo.constant.Constants.DEFAULT_RABBIT_QUEUE_NAME;

/**
 * Created by facheng on 17.03.17.
 */
@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        LOGGER.info("Sender : " + context);
        rabbitTemplate.convertAndSend(DEFAULT_RABBIT_QUEUE_NAME, context);
    }
}
