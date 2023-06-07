package com.matiesmengo.news.controller;

import com.matiesmengo.news.data.Article;
import com.matiesmengo.news.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping()
    public Flux<Article> getNews() {
        return newsService.getNews();
    }

    @GetMapping("/latest")
    public Mono<Article> getLatestNews() {
        return newsService.getLatestNews();
    }
}
