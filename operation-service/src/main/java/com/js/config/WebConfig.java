package com.js.config;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.client.AlbumsClient;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.net.URI;
import java.util.List;

@Configuration
public class WebConfig {
    private static final String USER_SERVICE = "user-service";
    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public WebClient webClient(ObjectMapper objectMapper) {
        List<ServiceInstance> instances = discoveryClient.getInstances(USER_SERVICE);
        int serviceIndex = RandomUtils.nextInt(0, instances.size());
        ServiceInstance serviceInstance = instances.get(serviceIndex);
        URI uri = serviceInstance.getUri();
        System.out.println(JSON.toJSONString(uri));

        return WebClient.builder()
                .baseUrl(uri.toString())
                .build();
    }

    @SneakyThrows
    @Bean
    public AlbumsClient postClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(AlbumsClient.class);
    }
}