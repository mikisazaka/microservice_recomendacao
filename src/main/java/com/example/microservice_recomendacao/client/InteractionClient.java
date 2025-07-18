package com.example.microservice_recomendacao.client;

import com.example.microservice_recomendacao.dto.BookDTO;
import com.example.microservice_recomendacao.dto.LikeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InteractionClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:8889")
    private String interactionServiceUrl;

    public List<LikeDTO> listarLikesPorUsuario(Long userId) {
        String url = interactionServiceUrl + "/like/" + userId;
        ResponseEntity<List<LikeDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<LikeDTO>>() {});
        return response.getBody();
    }

    public Boolean likeExiste(Long userId, Long bookId) {
        String url = interactionServiceUrl + "/like/existe/" + userId + "/" + bookId;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
