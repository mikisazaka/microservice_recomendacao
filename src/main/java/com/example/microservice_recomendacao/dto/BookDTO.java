package com.example.microservice_recomendacao.dto;

import com.example.microservice_recomendacao.enums.ContentRatingEnum;

public record BookDTO(
        Long id,
        String title,
        String author,
        Integer publishedYear,
        String gender,
        String pagesQuantity,
        ContentRatingEnum contentRating,
        String imagePath
) {
}
