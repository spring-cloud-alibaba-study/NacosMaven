package com.js.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.client.AlbumsClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {
    @Bean
    public WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl("https://user-service/")
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