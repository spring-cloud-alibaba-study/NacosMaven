package com.js.webclient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

public class BaseClientFactory {

    private LoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction;

    private LoadBalancerClient loadBalancerClient;

    public BaseClientFactory( LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
        this.loadBalancerExchangeFilterFunction = new LoadBalancerExchangeFilterFunction(loadBalancerClient);
    }
    
}