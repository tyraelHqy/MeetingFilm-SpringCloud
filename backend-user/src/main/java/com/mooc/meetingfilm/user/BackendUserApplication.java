package com.mooc.meetingfilm.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.mooc.meetingfilm.user.dao.mapper"})
@ComponentScan(basePackages = {"com.mooc.meetingfilm"})
public class BackendUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUserApplication.class, args);
    }

}
