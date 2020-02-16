package com.server.mq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void getMessage() throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                                                                throws IOException {
                System.out.println("消费消息："+new String(body, "UTF-8"));
                //确认接收消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(ConnectionUtil.QUEUE_NAME, deliverCallback);
    }

}
