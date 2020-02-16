package com.server.mq.controller;

import com.server.mq.service.RabbitmqMessageSend;
import com.server.mq.service.RabbitmqMessageSendTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    RabbitmqMessageSend rabbitmqMessageSend;
    @Autowired
    RabbitmqMessageSendTopic rabbitmqMessageSendTopic;

    @RequestMapping("/order.do")
    public  Object order(String message,String rouingKey,String name){
//        for(int i = 1;i<=3000;i++){
//            rabbitmqMessageSend.sendMessage(message);
//        }
        rabbitmqMessageSend.sendMessage(message);

        //rabbitmqMessageSendTopic.sendMessage(message);
        return "ok";
    }

    @RequestMapping("/order2.do")
    public  Object order2(){
        rabbitmqMessageSend.sendMessageBackUp();

        //rabbitmqMessageSendTopic.sendMessage(message);
        return "ok2";
    }
}

