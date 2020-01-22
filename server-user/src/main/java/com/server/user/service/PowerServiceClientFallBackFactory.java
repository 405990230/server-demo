package com.server.user.service;

import com.server.user.util.R;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PowerServiceClientFallBackFactory implements FallbackFactory<PowerFeignClient> {
    @Override
    public PowerFeignClient create(Throwable throwable) {
        return new PowerFeignClient() {
            @Override
            public Object getPower() {
                String message = throwable.getMessage();
                return R.error("feign降级");
            }

            @Override
            public Object getPowerList() {
                String message = throwable.getMessage();
                return R.error("feign降级");
            }
        };
    }
}
