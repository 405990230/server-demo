package com.server.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("111.231.203.94",5672);
        //我这里直接在构造方法传入了
        //        connectionFactory.setHost();
        //        connectionFactory.setPort();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("my_vhost");
        //设置开启发送方确认模式
//        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 配置手动确认，可以配置多个
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory
    simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory =
                new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消息预取的数量
        simpleRabbitListenerContainerFactory.setPrefetchCount(500);
        return simpleRabbitListenerContainerFactory;
    }

    /**
     * 配置手动确认，可以配置多个
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory
    otherRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory =
                new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消息预取的数量
        simpleRabbitListenerContainerFactory.setPrefetchCount(2500);
        return simpleRabbitListenerContainerFactory;
    }

}
