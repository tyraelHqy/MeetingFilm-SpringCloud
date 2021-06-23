package com.mooc.meetingfilm.consumer.controller;

import com.mooc.meetingfilm.consumer.feign.ProviderApi;
import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import com.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI serviceAPI;

    @Resource
    private ProviderApi providerApi;

    @RequestMapping("/sayHello")
    public String sayHello(String message){

        return serviceAPI.sayHello(message);
    }

    @RequestMapping("/sayHello/feign")
    public String sayHelloFeign(String message){

        System.out.println("feign message="+message);
        return providerApi.invokerProviderController(message);
    }

    @RequestMapping(value = "/sayHello/post")
    public String sayHelloFeign(String author, String providerId, UserModel userModel){

        log.info("author:{},providerId:{},UserModel:{}",author,providerId,userModel);

        return providerApi.providerPost(author,providerId,userModel);
    }

}
