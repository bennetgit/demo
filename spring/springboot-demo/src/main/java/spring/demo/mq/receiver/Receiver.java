package spring.demo.mq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static spring.demo.constant.Constants.DEFAULT_RABBIT_QUEUE_NAME;

/**
 * Created by facheng on 17.03.17.
 */
@Component
@RabbitListener(queues = DEFAULT_RABBIT_QUEUE_NAME)
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String say) {
        LOGGER.info("what this? {}", say);
    }

}
