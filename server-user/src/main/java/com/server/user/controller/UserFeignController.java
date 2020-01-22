package com.server.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.server.user.service.OrderFeignClient;
import com.server.user.service.PowerFeignClient;
import com.server.user.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 想要咨询vip课程相关的同学加一下安其拉老师QQ：3164703201
 * 想要往期视频的同学加一下木兰老师QQ：2746251334
 * author：鲁班学院-商鞅老师
 */
@RestController
public class UserFeignController {

    @Autowired
    private PowerFeignClient powerFeignClient;
    @Autowired
    private OrderFeignClient orderFeignClient;

    @RequestMapping("/getFeignPower.do")
    @HystrixCommand(fallbackMethod = "getFeignPowerFullBack")
    public R getFeignPower(String name){
        System.out.println("调用一次");
        return R.success("操作成功",powerFeignClient.getPower());
    }

    @RequestMapping("/getFeignPowerError.do")
    public R getFeignPowerError(String name){
        return R.success("操作成功",powerFeignClient.getPower());
    }

    @RequestMapping("/getFeignPowerLists.do")
    public R getPowerList(){
        return R.success("操作成功",powerFeignClient.getPowerList());
    }


    @RequestMapping("/getFeignOrder.do")
    @HystrixCommand(fallbackMethod = "getFeignOrderFullBack",
            threadPoolKey = "order",
            threadPoolProperties ={@HystrixProperty(name = "coreSize",value = "5")
                                  ,@HystrixProperty(name = "maxQueueSize",value = "1")})
    public R getFeignOrder(){
        System.out.println("调用一次");
        return R.success("操作成功",orderFeignClient.getOrder());
    }

    public R getFeignPowerFullBack(String name){
        System.out.println(name);
        return R.error("降级信息");
    }


    public R getFeignOrderFullBack(){
        return R.error("系统繁忙，稍后再试！");
    }
}
