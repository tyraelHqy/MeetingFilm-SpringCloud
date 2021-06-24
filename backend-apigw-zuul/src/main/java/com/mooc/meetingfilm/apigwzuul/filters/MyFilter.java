package com.mooc.meetingfilm.apigwzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
public class MyFilter extends ZuulFilter {

    /**
     * Filter 类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要拦截
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        // Threadlocal
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while ((headerNames.hasMoreElements())){
            String headname = headerNames.nextElement();
            log.info("headName:{},headvalue:{}",headname,request.getHeader(headname));
        }

        return null;
    }
}
