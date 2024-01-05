package com.example.javanewsrss.service;


import com.example.javanewsrss.service.fetch.FetchNews;
import com.example.javanewsrss.service.parse.NewsParser;
import com.example.javanewsrss.exception.FetchNewsExceptions;
import com.example.javanewsrss.model.entities.News;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.javanewsrss.helper.JsonHelper.xmlToJson;

@Service
@AllArgsConstructor
public class NewsProcessingManager {
    private final NewsCacheService newsCacheService;
    private final FetchNews fetchClient;


    public List<News> fetchAndProcessNews(String url, NewsParser newsParser) throws FetchNewsExceptions {
        String data = fetchClient.fetchNews(url);
        JSONObject jsonObject = xmlToJson(data);

        return newsParser.parse(jsonObject).stream()
                .filter(news -> news.getContent() != null && !news.getContent().trim().isEmpty())
                .filter(news -> {
                    boolean isCached = newsCacheService.isUrlCached(news.getLink());
                    if (!isCached) {
                        newsCacheService.cacheUrl(news.getLink());
                    }
                    return !isCached;
                })
                .toList();
    }

}
