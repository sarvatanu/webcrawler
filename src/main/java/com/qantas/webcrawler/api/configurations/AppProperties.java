    package com.qantas.webcrawler.api.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "app")
@EnableSwagger2
@Validated
public class AppProperties {

    @Min(0)
    private int defaultDepth;

    @Min(0)
    private int maxDepthAllowed;


    @Min(5)
    private int connectionTimeOut;

    private boolean followRedirects;

    public int getDefaultDepth() {
        return defaultDepth;
    }

    public void setDefaultDepth(final int defaultDepth) {
        this.defaultDepth = defaultDepth;
    }

    public int getMaxDepthAllowed() {
        return maxDepthAllowed;
    }

    public void setMaxDepthAllowed(final int maxDepthAllowed) {
        this.maxDepthAllowed = maxDepthAllowed;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(final int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public boolean isFollowRedirects() {
        return followRedirects;
    }

    public void setFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

}
