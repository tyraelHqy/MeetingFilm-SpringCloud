package com.mooc.hystrix.show.command;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandDemo extends HystrixCommand<String> {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommandDemo(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(false)
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThreadPool"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.defaultSetter()
                        .withCoreSize(2)
                        .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
                        .withMaxQueueSize(2)
                ));

        this.name = name;
    }

    @Override
    protected String getFallback() {
        String result = "Fallback name : "+name;
        System.err.println(result + ", currentThread-"+Thread.currentThread().getName());
        return result;
    }

    // 单词请求调用的业务方法
    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWOrld : " + name;

//        Thread.sleep(800);

        log.error(result + ", currentThread-" + Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
