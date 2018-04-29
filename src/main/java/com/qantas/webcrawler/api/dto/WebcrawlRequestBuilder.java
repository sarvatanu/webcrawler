package com.qantas.webcrawler.api.dto;

import com.qantas.webcrawler.api.context.TransactionContext;

public class WebcrawlRequestBuilder {
    private String webUrl;
    private Integer depth = 1;
    private TransactionContext transactionContext;

    public WebcrawlRequestBuilder withTransactionContext(final TransactionContext transactionContext) {
        this.transactionContext = transactionContext;
        return this;
    }

    public WebcrawlRequestBuilder withWebUrl(final String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public WebcrawlRequestBuilder withDepth(final Integer depth) {
        this.depth = depth;
        return this;
    }

    public WebcrawlRequest build() {
        if (transactionContext == null) {
            throw new IllegalArgumentException("Transaction context is not set");
        }
        if (webUrl == null) {
            throw new IllegalArgumentException("Web URL is not supplied.");
        }

        if (depth < 0) {
            throw new IllegalArgumentException("Invalid depth supplied.");
        }

        WebcrawlRequest webcrawlRequest = new WebcrawlRequest(webUrl, depth, transactionContext);
        return webcrawlRequest;
    }
}
