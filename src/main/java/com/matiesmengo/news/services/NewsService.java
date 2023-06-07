package com.matiesmengo.news.services;

import com.matiesmengo.news.data.Article;
import com.matiesmengo.news.factory.NewsApiFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NewsService {

    @Autowired
    NewsApiFactory newsApiFactory;

    public Flux<Article> getNews() {
        return newsApiFactory.getNews();
    }

    public Mono<Article> getLatestNews() {
        return newsApiFactory.getLatestNews();
    }
}
