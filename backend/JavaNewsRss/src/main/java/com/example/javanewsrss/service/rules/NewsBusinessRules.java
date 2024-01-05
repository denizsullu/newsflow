package com.example.javanewsrss.service.rules;

import com.example.javanewsrss.exception.BusinessException;
import com.example.javanewsrss.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsBusinessRules {
    private final NewsRepository repository;

    public void checkIfPublisherExists(String publisher) {
        if (!repository.existsByPublisher(publisher)) {
            throw new BusinessException("Publisher is not found");
        }
    }

}
