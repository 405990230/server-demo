package com.server.mq;

import com.rabbitmq.client.*;

public class Producer {
    public static void sendByExchange(String message) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        // 声明exchange
        channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, "fanout");
        //交换机和队列绑定
        channel.queueBind(ConnectionUtil.QUEUE_NAME, ConnectionUtil.EXCHANGE_NAME, "");

        channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, "", null,
                message.getBytes());
        System.out.println("发送的信息为:" + message);
        channel.close();
        connection.close();
    }
}
