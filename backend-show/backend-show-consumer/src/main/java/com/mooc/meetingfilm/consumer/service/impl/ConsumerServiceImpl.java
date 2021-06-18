package com.mooc.meetingfilm.consumer.service.impl;

import com.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerServiceImpl implements ConsumerServiceAPI {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String sayHello(String message) {

        // 准备工作
//        String hostname = "localhost";
//        int port = 7101;
//        String uri = "/provider/sayHello?message="+message;

        // GET Register 动态获取服务端口
//        ServiceInstance serviceInstance = eurekaClient.choose("hello-service-provider");
//        int port = serviceInstance.getPort();
//        String hostname = serviceInstance.getHost();
        String uri = "/provider/sayHello?message="+message;

        String url = "http://hello-service-provider:"+uri;

        // invoke provider test
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
