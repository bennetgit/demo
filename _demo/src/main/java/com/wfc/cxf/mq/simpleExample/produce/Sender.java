package com.wfc.cxf.mq.simpleExample.produce;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hahahh";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("Send:'" + message + "'");
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
