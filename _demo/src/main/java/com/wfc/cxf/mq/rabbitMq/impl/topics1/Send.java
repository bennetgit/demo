package com.wfc.cxf.mq.rabbitMq.impl.topics1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by fcw on 03.06.16.
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {

        // create conection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World, wang";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.print(" [x] sent '" + message + "'");

        channel.close();
        connection.close();

    }
}
