package com.bigfang.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PostRequestFilter
 * @Description TODO
 * @Author yaoyong.fang
 * @Date 2019/12/30 17:52
 * @Version 1.0
 **/
@Component
@Slf4j
public class PostRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER - 1;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() throws ZuulException {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final long startTime = (long) ctx.get("startTime");
        final String requestURI = request.getRequestURI();
        final long duration = System.currentTimeMillis() - startTime;
        log.info("uri:" + requestURI + ",duration:" + duration / 100 + "ms");
        return null;
    }
}
