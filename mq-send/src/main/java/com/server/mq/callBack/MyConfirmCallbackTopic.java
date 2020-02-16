package com.server.mq.callBack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class MyConfirmCallbackTopic implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MyConfirmCallbackTopic 2");
        System.out.println(correlationData);
        System.out.println(ack);
        System.out.println(cause);
    }
}
