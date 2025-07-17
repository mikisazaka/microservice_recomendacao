package com.example.microservice_recomendacao.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InteractionClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:8889")
    private String interactionServiceUrl;
}
