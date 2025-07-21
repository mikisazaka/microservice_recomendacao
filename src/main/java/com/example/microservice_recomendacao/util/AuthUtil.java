package com.example.microservice_recomendacao.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthUtil {

    public String getAuthorizationToken() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            String header = request.getHeader("Authorization");
            return header != null ? header.replace("Bearer ", "") : null;
        }
        return null;
    }
}
