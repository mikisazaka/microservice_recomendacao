package com.example.microservice_recomendacao.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin(origins = "http://localhost:4200")
public class RecomendacaoController {

}
