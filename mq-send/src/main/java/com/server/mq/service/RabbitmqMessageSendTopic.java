package com.server.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 需要咨询java高级VIP课程的同学可以加安其拉老师的QQ：3164703201
 * 需要往期视频资料的同学可以加木兰老师的QQ:2746251334
 * author：鲁班学院-商鞅老师
 */
@Component
public class RabbitmqMessageSendTopic {

    @Autowired
    RabbitTemplate rabbitTemplate;


//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback(new MyConfirmCallbackTopic());
//    }

    public void  sendMessage(String message){
        CorrelationData correlationData = new CorrelationData("订单ID topic");
        //topic.key
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("topic","123456");
        rabbitTemplate.convertAndSend("topicExchange", "topic.b", message,correlationData);
    }

}
