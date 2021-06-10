package com.mooc.meetingfilm.hall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.mooc.meetingfilm.hall.dao.mapper"})
@ComponentScan(basePackages = {"com.mooc.meetingfilm"})
public class BackendHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendHallApplication.class, args);
    }

}
