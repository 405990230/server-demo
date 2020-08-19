package com.server.user.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 想要咨询vip课程相关的同学加一下安其拉老师QQ：3164703201
 * 想要往期视频的同学加一下木兰老师QQ：2746251334
 * author：鲁班学院-商鞅老师
 */
@ComponentScan("com")
@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    /**
     * ComponentScan可以扫描到实现了IRule的类，那么所有的微服务都会使用这个策略
     */
    @Bean
    public IRule iRule1() throws UnknownHostException {
        //return new RandomRule();
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        if("192.168.109.139".equals(InetAddress.getLocalHost().getHostAddress())){
            return new RandomRule();
        } else {
            return new RoundRobinRule();
        }
    }

//    @Bean
//    public TomcatServletWebServerFactory tomcat(){
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.setPort(5000);
//        return  tomcat;
//    }
}
