package com.qantas.webcrawler.api.dto;

import org.jsoup.select.Elements;

public class PageInfo {

    private String title;

    private String url;

    private Elements links;


    public PageInfo(final String title,
                    final String url,
                    final Elements links) {
        this.title = title;
        this.url = url;
        this.links = links;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Elements getLinks() {
        return links;
    }

    public void setLinks(final Elements links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", links=" + links +
                '}';
    }
}
