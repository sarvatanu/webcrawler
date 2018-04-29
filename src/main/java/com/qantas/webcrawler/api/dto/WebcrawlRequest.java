package com.qantas.webcrawler.api.dto;


import com.qantas.webcrawler.api.context.TransactionContext;

public class WebcrawlRequest {

    private String webUrl;
    private Integer depth;
    private TransactionContext transactionContext;

    WebcrawlRequest(final String webUrl,
                    final Integer depth,
                    final TransactionContext transactionContext) {
        this.webUrl = webUrl;
        this.depth = depth;
        this.transactionContext = transactionContext;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Integer getDepth() {
        return depth;
    }

    public TransactionContext getTransactionContext() {
        return transactionContext;
    }

    @Override
    public String toString() {
        return "WebcrawlRequest{" +
                "webUrl='" + webUrl + '\'' +
                ", depth=" + depth +
                ", transactionContext=" + transactionContext +
                '}';
    }
}
