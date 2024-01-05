package com.example.javanewsrss.config;


import okhttp3.OkHttpClient;
import org.bouncycastle.pqc.crypto.newhope.NHSecretKeyProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableScheduling
@EnableJpaAuditing
@EnableCaching
@Configuration
public class AppConfiguration {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
