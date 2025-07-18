package com.example.microservice_recomendacao.controller;

import com.example.microservice_recomendacao.dto.BookDTO;
import com.example.microservice_recomendacao.service.RecomendacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin(origins = "http://localhost:4200")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookDTO>> listarRecomendacoes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(recomendacaoService.listarRecomendacoes(userId));
    }
}
