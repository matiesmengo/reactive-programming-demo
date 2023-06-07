package com.matiesmengo.news.factory;

import com.matiesmengo.news.data.Article;
import com.matiesmengo.news.data.NewsArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class NewsApiFactory {
    private final WebClient webClient;

    @Value("${newsapi.apiKey}")
    private String apiKey;

    public NewsApiFactory(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://newsapi.org/v2/").build();
    }


    public Flux<Article> getNews() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("top-headlines")
                        .queryParam("country", "sa")
                        .queryParam("category", "technology")
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsArticleResponse.class)
                .flatMapIterable(NewsArticleResponse::getArticles)
                .onErrorResume(WebClientResponseException.class, error -> {
                    if (error.getStatusCode().value() == 404) return Flux.empty();
                    return Flux.error(error);
                });
    }

    public Mono<Article> getLatestNews() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("top-headlines")
                        .queryParam("country", "sa")
                        .queryParam("category", "technology")
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsArticleResponse.class)
                .flatMapIterable(NewsArticleResponse::getArticles)
                .next();
    }
}
