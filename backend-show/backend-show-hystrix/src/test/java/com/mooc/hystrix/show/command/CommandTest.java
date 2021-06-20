package com.mooc.hystrix.show.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandTest {

    @Test
    public void executeTest() {

        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");

        // 同步执行Command
        String reslut = commandDemo.execute();

        long endTime = System.currentTimeMillis();

        System.out.println("reslut = " + reslut + "， speeding = " + (endTime - beginTime));
    }

    @Test
    public void requestCache(){
        // 开启请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();
        long beginTime = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c1");

        // 同步执行Command
        String r1 = c1.execute();
        long endTime1 = System.currentTimeMillis();
        System.err.println("result = " + r1 + "， speeding = " + (endTime1 - beginTime));

        String r2 = c2.execute();
        long endTime2 = System.currentTimeMillis();
        System.err.println("result = " + r2 + "， speeding = " + (endTime2 - beginTime));

        String r3 = c3.execute();
        long endTime3 = System.currentTimeMillis();
        System.err.println("result = " + r3 + "， speeding = " + (endTime3 - beginTime));

        // 请求上下文关闭
        requestContext.close();

    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {

        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("queue");

        Future<String> queue = commandDemo.queue();

        long endTime = System.currentTimeMillis();
        System.out.println("future end， speeding = " + (endTime - beginTime));

        long endTime2 = System.currentTimeMillis();
        System.out.println("result= " + queue.get() + "， speeding = " + (endTime2 - beginTime));

    }

    @Test
    public void observeTest() {

        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> observe = commandDemo.observe();

        // 阻塞式调用
        String result = observe.toBlocking().single();

        long endTime = System.currentTimeMillis();
        System.out.println("reslut = " + result + "， speeding = " + (endTime - beginTime));

        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe, OnCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

                System.err.println("observe, OnError - throwable" + throwable);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.err.println("observe, OnNext result=" + result + "spend:" + (endTime - beginTime));
            }
        });
    }


    @Test
    public void toObserveTest() throws InterruptedException {

        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo1 = new CommandDemo("observe1");
        Observable<String> toObserve1 = commandDemo1.toObservable();

        CommandDemo commandDemo2 = new CommandDemo("observe2");
        Observable<String> toObserve2 = commandDemo2.toObservable();

        // 阻塞式调用
        String result = toObserve1.toBlocking().single();

        long endTime = System.currentTimeMillis();
        System.out.println("reslut = " + result + "， speeding = " + (endTime - beginTime));

        // 非阻塞式调用
        toObserve2.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe, OnCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

                System.err.println("observe, OnError - throwable" + throwable);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.err.println("observe, OnNext result=" + result + "spend:" + (endTime - beginTime));
            }
        });

        Thread.sleep(2000);
    }


    /**
     * @Description: 演示线程池内容
     * @Param: []
     * @return: void
     * @Author: jiangzh
     */
    @Test
    public void threadTest() throws ExecutionException, InterruptedException {
        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c3");
        CommandDemo c4 = new CommandDemo("c4");
        CommandDemo c5 = new CommandDemo("c5");

        Future<String> q1 = c1.queue();
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();
        Future<String> q5 = c5.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();
        String r5 = q5.get();

        System.out.println(r1+","+r2+","+r3+","+r4+","+r5);

        // 1,2,3,4,5
        // core 1,2  max 1
        // queue 2

    }


    /**
     * @Description: 信号量隔离测试
     * @Param: []
     * @return: void
     * @Author: jiangzh
     */
    @Test
    public void semTest() throws InterruptedException {
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        Thread.sleep(2000l);
    }

    @Test
    public void CBTest() throws InterruptedException {
        // 正确 - 业务
        CommandDemo c1 = new CommandDemo("imooc");
        System.out.println(c1.execute());

        // 错误 - 业务
        CommandDemo c2 = new CommandDemo("jiangzh-1");
        System.out.println(c2.execute());

        // 正确 - 业务
        Thread.sleep(1000l);
        CommandDemo c3 = new CommandDemo("imooc");
        System.out.println(c3.execute());

        // 半熔断状态
        Thread.sleep(5000l);
        // 错误 - 业务
//        CommandDemo c4 = new CommandDemo("jiangzh-2");
//        System.out.println(c4.execute());

        // 正确 - 业务
//        CommandDemo c5 = new CommandDemo("imooc");
//        System.out.println(c5.execute());


        // 成功
        CommandDemo c6 = new CommandDemo("imooc");
        System.out.println(c6.execute());

    }

    class MyThread extends Thread{

        private String name;
        public MyThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            CommandDemo commandDemo = new CommandDemo(name);
            System.out.println("commandDemo result="+commandDemo.execute());
        }
    }
}
