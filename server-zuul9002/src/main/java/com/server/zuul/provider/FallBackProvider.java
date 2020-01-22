package com.server.zuul.provider;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FallBackProvider implements FallbackProvider {
    /**
     * 制定为哪个微服务提供回退（这里写微服务名 写*代表所有微服务）
     * @return
     */
    @Override
    public String getRoute() {
        return "server-power";
    }
    /**
     *
     * 此方法需要返回一个ClientHttpResponse对象 ClientHttpResonse是一个接口，
     * 具体的回退逻辑要实现此接口
     * route：出错的微服务名 cause：出错的异常对象
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        //这里可以判断根据不同的异常来做不同的处理， 也可以不判断
        //完了之后调用response方法并根据异常类型传入HttpStatus
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        //这里返回一个ClientHttpResponse对象 并实现其中的方法，关于回退逻辑的详细，便在下面的方法中
        return new ClientHttpResponse() {
            //返回一个HttpStatus对象 这个对象是个枚举对象， 里面包含了一个status code 和reasonPhrase信息
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            //返回status的code 比如 404，500等
            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            //返回一个HttpStatus对象的reasonPhrase信息
            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            //close的时候调用的方法， 讲白了就是当降级信息全部响应完了之后调用的方法
            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                //把降级信息响应回前端
                return new ByteArrayInputStream("系统繁忙，稍后再试".getBytes());
            }

            //需要对响应报头设置的话可以在此设置
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
