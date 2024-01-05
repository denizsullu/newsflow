package com.example.javanewsrss.repository;

import com.example.javanewsrss.model.entities.News;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, String> {

    boolean existsByLink(String url); // if redis is not work, this will be work

    boolean existsByPublisher(String publisher);

    List<News> findAllByPublisherOrderByPublishedDateDesc(String publisher);

    List<News> findAllByOrderByPublishedDateDesc();

    News findByTitle(String title);

    @NotNull
    Optional<News> findById(@NotNull String uuid);

    @NotNull
    Page<News> findAll(@NotNull Pageable pageable);

}
