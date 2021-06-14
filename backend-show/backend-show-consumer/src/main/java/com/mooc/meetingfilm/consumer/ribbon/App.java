package com.mooc.meetingfilm.consumer.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

public class App {

    public static void main(String[] args) throws Exception {
        ConfigurationManager.loadPropertiesFromResources("tyrael.properties");  // 1
        System.err.println(ConfigurationManager.getConfigInstance().getProperty("tyrael-client.ribbon.listOfServers"));

        // 构建一个HttpClient
        RestClient client = (RestClient) ClientFactory.getNamedClient("tyrael-client");  // 2
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3
//        for (int i = 0; i < 5; i++)  {
//            HttpResponse response = client.executeWithLoadBalancer(request); // 4
//            System.err.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
//        }

        // 动态修改的serverlist
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.err.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "tyrael-client.ribbon.listOfServers", "www.qq.com:80,www.taobao.com:80"); // 5
        System.err.println("changing servers ...");
        Thread.sleep(3000); // 6
        for (int i = 0; i < 5; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.err.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats()); // 7
    }

}