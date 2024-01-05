package com.example.javanewsrss.service.fetch;

import com.example.javanewsrss.exception.FetchNewsExceptions;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class FetchWithRest implements FetchNews {
    private final RestTemplate restTemplate;

    @Override
    // **RestTemplate does not automatically follow redirects, such as from HTTP to HTTPS, but OkHttp does**
    public String fetchNews(String url) throws FetchNewsExceptions {

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
            if(!response.getStatusCode().is2xxSuccessful()){
                throw new FetchNewsExceptions("Http status code " + response.getStatusCode().value(),response.getStatusCode().value());
            }
            return response.getBody();
        } catch (RestClientException e) {
            throw new FetchNewsExceptions("Error fetching news: " + e.getMessage(), 500);
        }

    }
}
