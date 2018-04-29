package com.qantas.webcrawler.api.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonBuilder {

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode toJson(final Object data) {
        try {
            return mapper.valueToTree(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parse(final String src) {
        try {
            return mapper.readTree(src);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
