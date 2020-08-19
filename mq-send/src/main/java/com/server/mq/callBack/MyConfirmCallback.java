package com.server.mq.callBack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MyConfirmCallback 1");
        System.out.println(correlationData);
        System.out.println(ack);
        System.out.println(cause);
    }
}
