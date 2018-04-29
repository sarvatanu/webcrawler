package com.qantas.webcrawler.api.context;

import javax.servlet.http.HttpServletRequest;

public final class TransactionContext {
    private String subContext;
    private String method;
    private String path;
    private String applicationLabel;
    private String correlationID;
    private final Long startTime;

    public Long getStartTime() {
        return this.startTime;
    }

    TransactionContext(final HttpServletRequest request) {
        this.applicationLabel = request.getHeader("APPLICATION_LABEL");
        this.correlationID = request.getHeader("CORRELATION_ID");

        this.method = request.getMethod();
        this.path = request.getRequestURI();
        this.startTime = System.currentTimeMillis();
    }

    public String getSubContext() {
        return this.subContext;
    }

    public void setSubContext(final String subContext) {
        this.subContext = subContext;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getPath() {
        return this.path;
    }

    public void setUrl(final String path) {
        this.path = path;
    }

    public String getApplicationLabel() {
        return this.applicationLabel;
    }

    public void setApplicationLabel(final String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getCorrelationID() {
        return this.correlationID;
    }

    public void setCorrelationID(final String correlationID) {
        this.correlationID = correlationID;
    }
}
