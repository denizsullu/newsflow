package com.example.javanewsrss.service.parse;

import com.example.javanewsrss.model.entities.News;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service("bbc")
public class BBCParser implements NewsParser {

    @Override
    public List<News> parse(JSONObject jsonObject) {
        JSONArray itemsArray = jsonObject.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        Stream<JSONObject> stream = IntStream.range(0, itemsArray.length()).mapToObj(itemsArray::getJSONObject);

        return stream.map(item -> {
            JSONObject guidObject = item.getJSONObject("media:thumbnail");

            Map<String, Object> guidMap = guidObject.toMap();
            String imageUrl = (String) guidMap.get("url");

            return News.builder()
                    .title(item.getString("title"))
                    .link(item.getString("link"))
                    .content(item.getString("description"))
                    .publishedDate(LocalDateTime.parse(item.getString("pubDate"), formatter))
                    .publisher("BBC")
                    .imageUrl(imageUrl)
                    .build();
        }).collect(Collectors.toList());
    }



}
