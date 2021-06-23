package com.mooc.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayHello",method = RequestMethod.GET)
    public String providerSayHello(String message) {
        log.info("provider say hello port:{}, message:{}", port, message);
        return "provider say hello port:" + port + ", message: " + message;

    }

    @RequestMapping(value = "/providers/{providerId}/sayHello",method = RequestMethod.POST)
    public String postTest(
            @RequestHeader("author")String author,
            @PathVariable("providerId") String providerId,
            @RequestBody String json
    ) {
        log.info("provider say hello port:{},author:{} ,providerId:{} ,message:{}", port, author,providerId,json);
        return "provider say hello port:" + port + ", message: " + json;

    }

}
