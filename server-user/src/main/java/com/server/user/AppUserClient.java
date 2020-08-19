package com.server.user;

import com.server.user.rule.OrderRuleConfig;
import com.server.user.rule.PowerRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@RibbonClients({
        @RibbonClient(name = "SERVER-ORDER",configuration = OrderRuleConfig.class),
        @RibbonClient(name = "SERVER-POWER",configuration = PowerRuleConfig.class)
})
@EnableFeignClients
@EnableHystrix
public class AppUserClient {
    public static void main(String[] args) {
        SpringApplication.run(AppUserClient.class);
    }
//    @Bean
//    public ServletRegistrationBean getServlet(){
//
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);  //系统启动时加载顺序
//        registrationBean.addUrlMappings("/actuator/hystrix.stream");//路径
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
}

