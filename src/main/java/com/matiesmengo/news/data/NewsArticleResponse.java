package com.matiesmengo.news.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticleResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;
}
