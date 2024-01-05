package com.example.javanewsrss.service.fetch;

import com.example.javanewsrss.exception.FetchNewsExceptions;
import lombok.AllArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;


@AllArgsConstructor
@Service
public class FetchClient implements FetchNews {
    private final OkHttpClient client;

    @Override
    public String fetchNews(String url) throws FetchNewsExceptions {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            if (!response.isSuccessful()) {
                throw new FetchNewsExceptions("Http status code " + response.code(), response.code());
            }
            return response.body().string();
        } catch (IOException e) {
            throw new FetchNewsExceptions(e.getMessage(), 500);
        }
    }
}
