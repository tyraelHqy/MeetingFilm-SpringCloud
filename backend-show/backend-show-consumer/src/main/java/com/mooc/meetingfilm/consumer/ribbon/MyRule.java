package com.mooc.meetingfilm.consumer.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

public class MyRule extends AbstractLoadBalancerRule {

    public MyRule(){

    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        // 灰度发布 10%的流量在新的功能~90%的流量在旧的功能

        // 对10%有没有限制，已经访问过新功能的流量不能再访问旧的功能

        // 1. 定义一个新功能的ServerList
        // up all

        // 引入缓存，是个请求里随机选择2~3个请求进入新功能的ServerList

            // 如果是，则直接访问新的ServerList

            // 如果不是，则继续后续判断

        // 无论传入什么 都返回null
        return null;
    }
}
