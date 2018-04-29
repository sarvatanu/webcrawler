package com.qantas.webcrawler.api.exceptions;

public class NoLinksException extends  RuntimeException {

    private Integer code;

    public NoLinksException(final String message,
                            final Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
