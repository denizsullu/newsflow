package com.example.javanewsrss.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Constants {


    @Value("${news.bbc.url}")
    private String bbcUrl;
    @Value("${news.ntv.url}")
    private String ntvUrl;
    @Value("${news.sozcu.url}")
    private String sozcu;


}

