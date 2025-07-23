package com.example.javanewsrss.controller;

import com.example.javanewsrss.controller.validation.NewsNameConstraint;
import com.example.javanewsrss.model.dtos.responses.GetByUUID;
import com.example.javanewsrss.service.NewsService;
import com.example.javanewsrss.model.dtos.responses.GetAllNewsResponse;

import com.example.javanewsrss.model.dtos.responses.GetNewsByPublisherResponse;
import com.example.javanewsrss.model.dtos.responses.GetPagination;
import com.example.javanewsrss.core.utilities.results.DataResult;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/news")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class NewsController {
    private final NewsService newsService;


    @GetMapping("/getAllNewsResponses")
    @Cacheable(value = "getAllNewsResponses")
    @RateLimiter(name = "newsRateLimiter")
    public DataResult<List<GetAllNewsResponse>> getAllNewsResponses() {

        return newsService.getAllNewsResponses();

    }

    @GetMapping("/publisher/{publisher}")
    @Cacheable(value = "getNewsByPublisherResponses")
    @RateLimiter(name = "newsRateLimiter")
    public DataResult<List<GetNewsByPublisherResponse>> getNewsByPublisherResponses(@PathVariable @NewsNameConstraint String publisher) {
        return newsService.getNewsByPublisherResponses(publisher);
    }

    @GetMapping("/getNewsPageable")
    @Cacheable(value = "getNewsPageable")
    @RateLimiter(name = "newsRateLimiter")
    public DataResult<Page<GetPagination>> getNewsPageable(@RequestParam int page,
                                                           @RequestParam int size) {
        return newsService.getNewsPageable(PageRequest.of(page, size, Sort.by("publishedDate")));
    }

    @GetMapping("/findByUUID")
    @Cacheable(value = "findByUUID")
    @RateLimiter(name = "newsRateLimiter")
    public DataResult<GetByUUID> findByNewsId(@RequestParam String uuid) {
        return newsService.findByNewsId(uuid);
    }


}
