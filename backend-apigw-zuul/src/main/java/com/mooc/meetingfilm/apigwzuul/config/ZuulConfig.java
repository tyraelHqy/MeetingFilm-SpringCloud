package com.mooc.meetingfilm.apigwzuul.config;

import com.mooc.meetingfilm.apigwzuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }

}
