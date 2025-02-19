package com.capacity.microservice.capacity_microservice.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.capacity.microservice.capacity_microservice.infrastructure.utils.constants.InfraConstans.TECHNOLOGY_MICROSERVICE;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(TECHNOLOGY_MICROSERVICE).build();
    }
}
