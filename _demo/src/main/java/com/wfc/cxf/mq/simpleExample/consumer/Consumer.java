package com.wfc.cxf.mq.simpleExample.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException,
            InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery deliver = consumer.nextDelivery();
            String message = new String(deliver.getBody());
            System.out.println("Received: '" + message + "'");
        }
    }
}
