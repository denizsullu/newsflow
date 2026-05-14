package com.example.javanewsrss.service;

import com.example.javanewsrss.service.parse.NewsParser;
import com.example.javanewsrss.constant.Constants;
import com.example.javanewsrss.exception.FetchNewsExceptions;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
@ConditionalOnProperty(name = "news.scheduler.enabled", havingValue = "true", matchIfMissing = true)
public class NewsScheduledTasks {
    private final NewsManager newsManager;
    private final Map<String, NewsParser> newsParserMap;
    private final Constants constants;

    @Scheduled(fixedRate = 1800000)
    @CacheEvict(value = {"getNewsByPublisherResponses", "getAllNewsResponses", "getNewsPageable", "findByUUID"}, allEntries = true)
    public void fetchAndSaveNews() {
        fetchAndSavePublisher(constants.getBbcUrl(), newsParserMap.get("bbc"), "BBC");
        fetchAndSavePublisher(constants.getNtvUrl(), newsParserMap.get("ntv"), "NTV");
        fetchAndSavePublisher(constants.getSozcu(), newsParserMap.get("sozcu"), "Sozcu");
    }

    private void fetchAndSavePublisher(String url, NewsParser parser, String publisher) {
        try {
            newsManager.saveNews(url, parser);
            System.out.println(publisher + " news fetched");
        } catch (Exception e) {
            System.out.println(publisher + " fetch failed: " + e.getMessage());
        }
    }

}
