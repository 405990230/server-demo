package com.spring.lifecycle.callbacks;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class A  implements InitializingBean, DisposableBean {
    public A(){
        System.out.println("A cons");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct init");
    }

    @PostConstruct
    public void init2(){
        System.out.println("PostConstruct init2");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean destroy");
    }

    @PreDestroy
    public void destroy2(){
        System.out.println("PreDestroy destroy");
    }
}
