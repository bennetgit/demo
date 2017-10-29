package spring.demo.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static spring.demo.constant.Constants.DEFAULT_RABBIT_QUEUE_NAME;

/**
 * Created by facheng on 17.03.17.
 */
//@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue(DEFAULT_RABBIT_QUEUE_NAME);
    }
}
