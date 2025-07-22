package com.example.microservice_recomendacao.service;

import com.example.microservice_recomendacao.client.BookClient;
import com.example.microservice_recomendacao.client.InteractionClient;
import com.example.microservice_recomendacao.dto.BookDTO;
import com.example.microservice_recomendacao.dto.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    private final InteractionClient interactionClient;
    private final BookClient bookClient;

    public RecomendacaoService(InteractionClient interactionClient, BookClient bookClient) {
        this.interactionClient = interactionClient;
        this.bookClient = bookClient;
    }

    public List<BookDTO> listarRecomendacoes(Long userId) {
        List<LikeDTO> curtidas = interactionClient.listarLikesPorUsuario(userId);
        List<BookDTO> livrosCurtidos = new ArrayList<>();
        Set<Long> livrosCurtidosIds = curtidas.stream()
                .map(LikeDTO::bookId)
                .collect(Collectors.toSet());
        for(LikeDTO l : curtidas) {
            BookDTO livro = bookClient.getBookById(l.bookId());
            livrosCurtidos.add(livro);
        }

        HashMap<String, Integer> mapGenero = new LinkedHashMap<>();
        mapGenero.put("Romance", 0);
        mapGenero.put("Terror", 0);
        mapGenero.put("Fantasia", 0);
        mapGenero.put("Drama", 0);
        mapGenero.put("Mistério", 0);
        mapGenero.put("Suspense", 0);

        for(BookDTO livro : livrosCurtidos) {
            switch (livro.gender()) {
                case "Romance": {
                    mapGenero.put("Romance", mapGenero.get("Romance") + 1);
                    break;
                }
                case "Terror": {
                    mapGenero.put("Terror", mapGenero.get("Terror") + 1);
                    break;
                }
                case "Fantasia": {
                    mapGenero.put("Fantasia", mapGenero.get("Fantasia") + 1);
                    break;
                }
                case "Drama": {
                    mapGenero.put("Drama", mapGenero.get("Drama") + 1);
                    break;
                }
                case "Mistério": {
                    mapGenero.put("Mistério", mapGenero.get("Mistério") + 1);
                    break;
                }
                case "Suspense": {
                    mapGenero.put("Suspense", mapGenero.get("Suspense") + 1);
                    break;
                }
            }
        }

        int totalLivrosCurtidos = 0;
        for(Map.Entry<String, Integer> map : mapGenero.entrySet()) {
            totalLivrosCurtidos += map.getValue();
        }

        int qtdTotal = 0;
        for(Map.Entry<String, Integer> map : mapGenero.entrySet()) {
            Integer qtdCurtidas = map.getValue();
            double peso = ((double) qtdCurtidas / totalLivrosCurtidos) * 10;
            int pesoIndividual = (int) Math.round(peso);
            qtdTotal += pesoIndividual;
            mapGenero.put(map.getKey(), pesoIndividual);
        }

        int qtdRetirar = qtdTotal - 5;
        List<Map.Entry<String, Integer>> map = new ArrayList<>(mapGenero.entrySet());
        Collections.sort(map, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        for(int i = map.size() - 1; i >= 0; i--) {
            if(qtdRetirar > 0) {
                Map.Entry<String, Integer> entry = map.get(i);
                if(entry.getValue() > 0) {
                    String genero = entry.getKey();
                    mapGenero.put(genero, mapGenero.get(genero) - 1);
                    qtdRetirar--;
                }
            }
        }

        List<BookDTO> todosLivros = bookClient.getBooks();
        List<BookDTO> livrosRecomendados = new ArrayList<>();

        Map<String, Integer> mapOrdenado = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry : map) {
            mapOrdenado.put(entry.getKey(), entry.getValue());
        }

        for(Map.Entry<String, Integer> genero : mapGenero.entrySet()) {
            int qtd = genero.getValue();
            for(BookDTO livro : todosLivros) {
                if(qtd > 0 && (livro.gender().equals(genero.getKey()) && !livrosCurtidosIds.contains(livro.id()))) {
                    livrosRecomendados.add(livro);
                    qtd--;
                }
            }
        }

        return livrosRecomendados;
    }
}
