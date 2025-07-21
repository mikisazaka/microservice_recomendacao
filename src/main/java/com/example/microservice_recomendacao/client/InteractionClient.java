package com.example.microservice_recomendacao.client;

import com.example.microservice_recomendacao.dto.BookDTO;
import com.example.microservice_recomendacao.dto.LikeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InteractionClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interact-service.url}")
    private String interactionServiceUrl;

    public List<LikeDTO> listarLikesPorUsuario(Long userId) {
        String url = interactionServiceUrl + "/like/" + userId;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) auth.getCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<LikeDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<LikeDTO>>() {});
        return response.getBody();
    }

    public Boolean likeExiste(Long userId, Long bookId) {
        String url = interactionServiceUrl + "/like/existe/" + userId + "/" + bookId;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) auth.getCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Boolean.class
        );

        return response.getBody();
    }
}
