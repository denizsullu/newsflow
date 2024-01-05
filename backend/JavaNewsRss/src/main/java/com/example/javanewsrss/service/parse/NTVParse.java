package com.example.javanewsrss.service.parse;

import com.example.javanewsrss.model.entities.News;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service("ntv")
public class NTVParse implements NewsParser {
    @Override
    public List<News> parse(JSONObject jsonObject) {
        JSONArray itemsArray = jsonObject.getJSONObject("feed").getJSONArray("entry");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        Stream<JSONObject> stream = IntStream.range(0, itemsArray.length()).mapToObj(itemsArray::getJSONObject);


        return stream.map(item -> {
            String htmlContent = item.getJSONObject("content").getString("content");
            Document doc = Jsoup.parse(htmlContent);
            Element img = doc.select("img").first();
            String imageUrl = "";
            if (img != null) {
                imageUrl = img.attr("src");
            }

            return News.builder()
                    .title(item.getJSONObject("title").getString("content"))
                    .link(item.getString("id"))
                    .content(htmlContent.replaceAll("<.*?>", ""))
                    .imageUrl(imageUrl)
                    .publishedDate(LocalDateTime.parse(item.getString("published"), formatter))
                    .publisher("NTV")
                    .build();
        }).collect(Collectors.toList());
}}
