package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "hello-service-provider",
//        fallback = ProviderFallbackAPIImpl.class
//        url = "http://localhost:7101"
        fallbackFactory = FallbackFactory.class
)
public interface ProviderApi {

    @RequestMapping(value = "/provider/sayHello", method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);

    @RequestMapping(value = "/providers/{providerId}/sayHello", method = RequestMethod.POST)
    String providerPost(
            @RequestHeader("author") String author,
            @PathVariable("providerId") String providerId,
            @RequestBody UserModel userModel);

}
