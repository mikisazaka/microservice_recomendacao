package com.example.microservice_recomendacao.dto;

import com.example.microservice_recomendacao.enums.UserRole;

public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        UserRole role
) {
}
