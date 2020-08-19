package com.server.user.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PowerRuleConfig {
    @Bean
    public IRule iRuleR(){
        //return  new CustomRule();
        return  new RoundRobinRule();
    }
}
