package com.example.microservice_recomendacao.client;

import com.example.microservice_recomendacao.dto.BookDTO;
import com.example.microservice_recomendacao.dto.LikeDTO;
import com.example.microservice_recomendacao.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BookClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:8887")
    private String bookServiceUrl;

    public BookDTO getBookById(Long id) {
        String url = bookServiceUrl + "/book/" + id;
        return restTemplate.getForObject(url, BookDTO.class);
    }

    public List<BookDTO> getBooks() {
        String url = bookServiceUrl + "/book";
        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BookDTO>>() {});
        return response.getBody();
    }
}
