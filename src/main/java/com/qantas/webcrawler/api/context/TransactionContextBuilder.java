package com.qantas.webcrawler.api.context;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class TransactionContextBuilder {

    private HttpServletRequest httpServletRequest;

    public TransactionContextBuilder withHttpRequest(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

    public TransactionContext build() {
        if (httpServletRequest == null) {
            throw new IllegalArgumentException("Missing Http Request");
        }
        TransactionContext tranxContext = new TransactionContext(httpServletRequest);
        String appLabel = tranxContext.getApplicationLabel();
        if (appLabel == null) {
            tranxContext.setApplicationLabel("WEBCRAWLER");
        }

        String correlationId = tranxContext.getCorrelationID();
        if (correlationId == null) {
            UUID uuid = UUID.randomUUID();
            tranxContext.setCorrelationID(uuid.toString());
        }
        return tranxContext;
    }
}
