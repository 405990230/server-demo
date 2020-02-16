package com.server.mq.config;

import com.alibaba.fastjson.JSON;
import com.server.mq.callBack.MyConfirmCallback;
import com.server.mq.callBack.MyReturnCallback;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.server.mq")
public class RabbitmqConfig {

    /**
     * 配置连接
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("111.231.203.94",5672);
        //我这里直接在构造方法传入了
        //        connectionFactory.setHost();
        //        connectionFactory.setPort();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("my_vhost");
        //是否开启消息确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 创建direct交换机
     * @return
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 创建topic交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     *创建queue
     * @return
     */
    @Bean
    public Queue queue() {
        //名字 是否持久化
        return new Queue("directQueue", true);
    }


    /**
     *创建queue
     * @return
     */
    @Bean
    public Queue queue2() {
        //名字 是否持久化
        return new Queue("topicQueue", true);
    }

    /**
     *  directQueue 绑定路由建
     * @return
     */
    @Bean
    public Binding binding() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("direct.key");
    }

    /**
     * topicQueue 绑定路由建
     * @return
     */
    @Bean
    public Binding binding2() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * 设置 RabbitTemplate
     * @param connectionFactory
     * @return
     */
    @Bean
    //@Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        //注意 这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的
        //如果是yml配置的连接的话是不需要的
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //添加发送方确认模式方法
        rabbitTemplate.setConfirmCallback(new MyConfirmCallback());
        //开启mandatory模式（开启失败回调）
        rabbitTemplate.setMandatory(true);
        //添加失败回调方法
        rabbitTemplate.setReturnCallback(new MyReturnCallback());

        //设置消息格式
        rabbitTemplate.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/xml");
                messageProperties.setContentEncoding("UTF-8");
                Message message = new Message(JSON.toJSONBytes(o),messageProperties);
                System.out.println("调用了消息解析器");
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }
        });
        return  rabbitTemplate;
    }

    //------------备用交换机-------------




    /**
     * 备用交换机
     * @return
     */
    @Bean
    public DirectExchange alternateExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange","directExchangeTest_backup");
        return  new DirectExchange("directExchangeTest",false,false,map);
    }

    @Bean
    public Queue queueTest() {
        //名字 是否持久化
        return new Queue("queueTest", true);
    }

    @Bean
    public Binding bindingTest() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queueTest()).to(alternateExchange()).with("direct.test");
    }

    /**
     * 创建备用交换机
     * @return
     */
    @Bean
    public FanoutExchange directExchangeTest_backup() {
        return new FanoutExchange("directExchangeTest_backup");
    }

    /**
     * 绑定备用交换机和队列
     * @return
     */
    @Bean
    public Binding bindingQueue() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queueTest()).to(directExchangeTest_backup());
    }



    //-----------死信交换机----------

    @Bean
    public Queue queueNormal() {
        Map<String,Object> map = new HashMap<>();
        //设置消息的过期时间 单位毫秒
        map.put("x-message-ttl",10000);
        //设置附带的死信交换机
        map.put("x-dead-letter-exchange","deadExchange");
        //指定重定向的路由建 消息作废之后可以决定需不需要更改他的路由建 如果需要 就在这里指定
        map.put("x-dead-letter-routing-key","dead.key");
        return new Queue("queueNormal", true,false,false,map);
    }

    /**
     * 创建direct交换机
     * @return
     */
    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange("normalExchange");
    }

    @Bean
    public Binding bindingNormal() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queueNormal()).to(normalExchange()).with("normal.key");
    }

    /**
     * 创建死信交换机
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("deadExchange");
    }

    /**
     * 创建死信交换机对应的对列
     * @return
     */
    @Bean
    public Queue queueDead() {
        return new Queue("queueDead", true,false,false);
    }

    /**
     * 绑定死信交换机和队列
     * @return
     */
    @Bean
    public Binding bindingDead() {
        //绑定一个队列 to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queueDead()).to(deadExchange()).with("dead.key");
    }

}
