package com.example.javanewsrss.model.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPagination {
    private String title;
    private String link;
    private String content;
    private LocalDateTime publishedDate;
    private String publisher;
}
