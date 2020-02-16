package com.server.mq.service;

import com.server.mq.callBack.MyConfirmCallback;
import com.server.mq.callBack.MyReturnCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要咨询java高级VIP课程的同学可以加安其拉老师的QQ：3164703201
 * 需要往期视频资料的同学可以加木兰老师的QQ:2746251334
 * author：鲁班学院-商鞅老师
 */
@Component
public class RabbitmqMessageSend {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback(new MyConfirmCallback());
//    }

    /**
     * 死信交换机测试
     * @param message
     */
    public void  sendMessage(String message){
        CorrelationData correlationData = new CorrelationData("订单ID direct");
        //direct.key
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("direct","123456");
        //至于为什么调用这个API 后面会解释
        //参数介绍： 交换机名字，路由建， 消息内容，CorrelationData参数
        rabbitTemplate.convertAndSend("normalExchange", "normal.key", message,correlationData);
    }


    /**
     * 备用交换机测试
     */
    public void  sendMessageBackUp(){
        CorrelationData correlationData = new CorrelationData("订单ID direct");
        //direct.key
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        map.put("direct","123456");
        //至于为什么调用这个API 后面会解释
        //参数介绍： 交换机名字，路由建， 消息内容，CorrelationData参数
        rabbitTemplate.convertAndSend("directExchangeTest", "direct.waefsdf", map,correlationData);
    }
}
