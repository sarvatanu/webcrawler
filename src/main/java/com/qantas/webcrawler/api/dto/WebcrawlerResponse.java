package com.qantas.webcrawler.api.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qantas.webcrawler.api.utils.JsonBuilder;

public class WebcrawlerResponse {
    private JsonNode pageInfo;
    private Page page;

    private ObjectMapper mapper = new ObjectMapper();
    public WebcrawlerResponse(final Page page) {
        this.page = page;
    }

    public JsonNode getPageInfo() {
        return JsonBuilder.toJson(page);
    }
}
