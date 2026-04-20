package com.loun.booktracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoBookService {

    @Value("${kakao.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public KakaoBookService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .build();
    }

    public Object searchBooks(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v3/search/book")
                        .queryParam("query", query)
                        .queryParam("size", 10)
                        .build())
                .header("Authorization", "KakaoAK " + apiKey)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}