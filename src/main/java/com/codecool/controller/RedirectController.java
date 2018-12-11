package com.codecool.controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RedirectController {

    public void redirect(HttpExchange httpExchange, String contextName) throws IOException {

        Headers req = httpExchange.getRequestHeaders();
        String host = req.getFirst("Host");

        Headers map = httpExchange.getResponseHeaders();
        map.set("Content-Type", "text/html");
        String location = "http://" + host + contextName;
        map.set("Location", location);

        httpExchange.sendResponseHeaders(302, -1);
        httpExchange.close();
    }

    public Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");

            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
