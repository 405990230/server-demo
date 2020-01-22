package com.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter extends ZuulFilter {

    /**
     * 声明过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 声明过滤器优先级别
     * @return
     */
    @Override
    public int filterOrder() {

        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    /**
     * 判断该过滤器是否要执行， true表示执行， false表示不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = ctx.get(FilterConstants.REQUEST_URI_KEY).toString();
        System.out.println(request.getRemoteAddr()+"访问了"+request.getRequestURI()+"路由后的地址:"+s);
        return null;
    }
}
