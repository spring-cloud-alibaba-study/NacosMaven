package com.js.webclient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

public class LoadBalancerExchangeFilterFunction {

    public LoadBalancerExchangeFilterFunction(LoadBalancerClient loadBalancerClient) {

    }

    public WebClient getWebClientByServiceName(String serviceName){
        String baseUrl = "http://"+serviceName;
        ExchangeFilterFunction loadBalancerExchangeFilterFunction = null;
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }
}
