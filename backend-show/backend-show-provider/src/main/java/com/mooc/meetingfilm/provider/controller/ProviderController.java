package com.mooc.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/provider/sayHello")
    public String providerSayHello(String message) {
        log.info("provider say hello port:{}, message:{}", port, message);
        return "provider say hello port:" + port + ", message: " + message;

    }
}
