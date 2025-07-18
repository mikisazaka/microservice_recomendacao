package com.example.microservice_recomendacao.client;

import com.example.microservice_recomendacao.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:8888")
    private String userServiceUrl;

    public UserDTO getUserById(Long id) {
        String url = userServiceUrl + "/user/" + id;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
