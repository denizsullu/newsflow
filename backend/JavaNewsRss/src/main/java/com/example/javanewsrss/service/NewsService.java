package com.example.javanewsrss.service;

import com.example.javanewsrss.model.dtos.responses.GetAllNewsResponse;
import com.example.javanewsrss.model.dtos.responses.GetByUUID;
import com.example.javanewsrss.model.dtos.responses.GetNewsByPublisherResponse;
import com.example.javanewsrss.model.dtos.responses.GetPagination;
import com.example.javanewsrss.core.utilities.results.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    DataResult<List<GetAllNewsResponse>> getAllNewsResponses();

    DataResult<List<GetNewsByPublisherResponse>> getNewsByPublisherResponses(String publisher);

    DataResult<Page<GetPagination>> getNewsPageable(Pageable pageable);

    DataResult<GetByUUID> findByNewsId(String title);
}

