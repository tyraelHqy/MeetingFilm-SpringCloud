package com.mooc.meetingfilm.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient //DiscoverClient可以集成大部分的注册中心
@SpringBootApplication
public class BackendShowConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowConsumerApplication.class, args);
    }

}
