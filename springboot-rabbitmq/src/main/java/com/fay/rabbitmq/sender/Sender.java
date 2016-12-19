package com.fay.rabbitmq.sender;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Send : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }
}
