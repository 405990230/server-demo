package com.server.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "SERVER-POWER",fallback =PowerfeignFallBack.class )
@FeignClient(value = "SERVER-POWER",fallbackFactory = PowerServiceClientFallBackFactory.class)
public interface PowerFeignClient {
    @RequestMapping("/getPower.do")
    Object getPower();

    @RequestMapping("/getPowerLists.do")
    Object getPowerList();
}
