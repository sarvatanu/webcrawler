package com.qantas.webcrawler.api.dto;

import java.util.List;

public class ApiResponse {

    private Integer code;
    private String applicationLabel;
    private String time;
    private String correlationId;
    private String method;
    private Object data;
    private Integer status;
    private String message;
    private List<ApiError> errors;
    private String path;

    public ApiResponse() {
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getApplicationLabel() {
        return this.applicationLabel;
    }

    public void setApplicationLabel(final String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public void setCorrelationId(final String correlationId) {
        this.correlationId = correlationId;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public List<ApiError> getErrors() {
        return this.errors;
    }

    public void setErrors(final List<ApiError> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
