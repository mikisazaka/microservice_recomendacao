package com.example.microservice_recomendacao.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:8888")
    private String userServiceUrl;
}
