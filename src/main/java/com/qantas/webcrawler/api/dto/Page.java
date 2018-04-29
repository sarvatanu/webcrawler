package com.qantas.webcrawler.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page {

    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;

    @JsonProperty("valid")
    private Boolean valid;

    @JsonProperty("nodes")
    private List<Page> nodes;


    public Page(final String url) {
        this.url = url;
        valid = false;
    }

    public Page addNodesItem(final Page nodesItem) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        if (nodesItem != null) {
            nodes.add(nodesItem);
        }
        return this;
    }

    public Page valid(final Boolean valid) {
        this.valid = valid;
        return this;
    }

    public Page title(final String title) {
        this.title = title;
        return this;
    }

    @ApiModelProperty(value = "page Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @ApiModelProperty(value = "Page title")
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(final Boolean valid) {
        this.valid = valid;
    }

    @ApiModelProperty(value = "")
    public List<Page> getNodes() {
        return nodes;
    }

    public void setNodes(final List<Page> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;
        Page page = (Page) o;
        return Objects.equals(getUrl(), page.getUrl()) &&
                Objects.equals(getTitle(), page.getTitle()) &&
                Objects.equals(getValid(), page.getValid()) &&
                Objects.equals(getNodes(), page.getNodes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUrl(), getTitle(), getValid(), getNodes());
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", valid=" + valid +
                ", nodes=" + nodes +
                '}';
    }
}
