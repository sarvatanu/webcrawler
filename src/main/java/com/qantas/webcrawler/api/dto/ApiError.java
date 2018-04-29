package com.qantas.webcrawler.api.dto;

public class ApiError {

    private String code;
    private String message;

    public ApiError() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
