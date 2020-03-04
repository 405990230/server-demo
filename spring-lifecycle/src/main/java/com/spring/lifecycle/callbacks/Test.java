package com.spring.lifecycle.callbacks;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean(A.class);
        ac.close();
    }
}
