package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.stereotype.Service;

@Service
public class ProviderFallbackAPIImpl implements ProviderApi{
    @Override
    public String invokerProviderController(String message) {
        return "invokerProviderController fallback message = "+message;
    }

    @Override
    public String providerPost(String author, String providerId, UserModel userModel) {
        return "providerPost fallback userModel = "+userModel;
    }
}
