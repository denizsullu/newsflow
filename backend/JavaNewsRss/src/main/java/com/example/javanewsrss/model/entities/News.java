package com.example.javanewsrss.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class) // for auto create datetime
public class News extends BaseEntity {

    private String title;

    @Column(unique = true)
    private String link;


    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime publishedDate;

    private String publisher;
    private String imageUrl;
}
