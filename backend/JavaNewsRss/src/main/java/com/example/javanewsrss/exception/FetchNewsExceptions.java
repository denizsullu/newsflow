package com.example.javanewsrss.exception;

import lombok.Getter;

import java.io.IOException;

@Getter
public class FetchNewsExceptions extends IOException {
    private final int statusCode;

    public FetchNewsExceptions(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
