package com.example.javanewsrss.service.fetch;

import com.example.javanewsrss.exception.FetchNewsExceptions;

public interface FetchNews {

    String fetchNews(String url) throws FetchNewsExceptions;
}
