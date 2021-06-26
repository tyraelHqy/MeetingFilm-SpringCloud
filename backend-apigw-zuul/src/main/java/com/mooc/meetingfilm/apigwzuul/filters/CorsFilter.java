﻿package com.mooc.meetingfilm.apigwzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * @author : jiangzh
 * @program : com.mooc.jiangzh.springcloud.filters.pre
 * @description : 解决跨域问题
 **/
@Component
public class CorsFilter extends ZuulFilter {

  public String filterType() {
    return "pre";
  }

  public int filterOrder() {
    return 0;
  }

  public boolean shouldFilter() {
    return true;
  }

  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    // 跨域
    HttpServletResponse response = ctx.getResponse();
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
    response.setHeader("Access-Control-Allow-Headers","DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range,Authorization");
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    /**
     * 跨域资源共享
     *  - http协议规定的安全策略
     *  - 配置资源共享的方式和目标
     *
     *  前端：node+vue ——> admin.meetingfilm.com
     *  后端：SpringBoot ——> backend.meetingfilm.com
     */

    return null;
  }

}