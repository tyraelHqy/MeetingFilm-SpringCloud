package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.stereotype.Service;

@Service
public class FallbackFactory implements feign.hystrix.FallbackFactory {
    @Override
    public ProviderApi create(Throwable throwable) {
        return new ProviderApi() {
            @Override
            public String invokerProviderController(String message) {
                return " factory invokerProviderController fallback message = "+message;
            }

            @Override
            public String providerPost(String author, String providerId, UserModel userModel) {
                return " factory invokerProviderController fallback userModel = "+userModel;
            }
        };
    }
}
