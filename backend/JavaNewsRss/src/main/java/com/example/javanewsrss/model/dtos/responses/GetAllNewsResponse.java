package com.example.javanewsrss.model.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllNewsResponse{
    private String id;
    private String title;
    private String link;
    private String content;
    private LocalDateTime publishedDate;
    private String publisher;
    private String imageUrl;
}
