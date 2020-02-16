package com.server.mq;

import com.server.mq.config.RabbitmqConfig;
import com.server.mq.service.RabbitmqMessageSend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 需要咨询java高级VIP课程的同学可以木兰老师的QQ：2746251334
 * 需要往期视频的同学可以加加安其拉老师的QQ：3164703201
 * author：鲁班学院-商鞅老师
 */
@SpringBootApplication
public class AppMqSend {

    public static void main(String[] args) {
        SpringApplication.run(AppMqSend.class);

//        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(RabbitmqConfig.class);
//        RabbitmqMessageSend bean = annotationConfigApplicationContext.getBean(RabbitmqMessageSend.class);
//        bean.sendMessage("test");
//        annotationConfigApplicationContext.close();
    }
}
