package com.server.mq.callBack;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback{
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        System.out.println("MyReturnCallback 1");
        System.out.println(message);
        System.out.println(replyCode);
        System.out.println(replyText);
        System.out.println(exchange);
        System.out.println(routingKey);
    }
}
