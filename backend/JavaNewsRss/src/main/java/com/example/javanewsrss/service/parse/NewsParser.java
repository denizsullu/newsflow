package com.example.javanewsrss.service.parse;

import com.example.javanewsrss.model.entities.News;
import org.json.JSONObject;

import java.util.List;

public interface NewsParser {

    List<News> parse(JSONObject jsonObject);
}
