package com.qantas.webcrawler.api.exceptions;

public class WebcrawlerException extends RuntimeException {

    private int code;
    public WebcrawlerException(final String message,
                               final int code) {
        super(message);
        this.code = code;
    }

    public WebcrawlerException(final String message,
                               final Throwable tr,
                               final int code) {
        super(message, tr);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
