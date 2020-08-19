package com.server.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVER-ORDER")
public interface OrderFeignClient {
    String API_ROOT = "/api";

    @RequestMapping("/getOrder.do")
    Object getOrder();

}
