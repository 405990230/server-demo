package com.server.user.service;

import com.server.user.util.R;
import org.springframework.stereotype.Component;

/**
 * 想要咨询vip课程相关的同学加一下木兰老师QQ：2746251334
 * 想要往期视频的同学加一下安其拉老师QQ：3164703201
 * author：鲁班学院-商鞅老师
 */
@Component //一定要加！！！！！！！！！！！
public class PowerfeignFallBack implements PowerFeignClient {
    @Override
    public Object getPower() {
        return R.error("power服务暂时不可用");
    }

    @Override
    public Object getPowerList() {
        return R.error("getPowerList暂时不可用");
    }
}
