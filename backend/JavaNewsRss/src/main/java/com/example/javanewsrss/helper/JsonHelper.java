package com.example.javanewsrss.helper;


import org.json.JSONObject;
import org.json.XML;


public class JsonHelper {

    public static JSONObject xmlToJson(String data) {

        return XML.toJSONObject(data);
    }


}
