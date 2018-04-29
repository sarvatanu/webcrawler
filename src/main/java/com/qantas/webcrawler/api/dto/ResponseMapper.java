package com.qantas.webcrawler.api.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.qantas.webcrawler.api.context.TransactionContext;
import com.qantas.webcrawler.api.exceptions.WebcrawlerException;
import com.qantas.webcrawler.api.utils.DateUtils;
import com.qantas.webcrawler.api.utils.JsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

public class ResponseMapper {
    private ResponseMapper() {
        throw new IllegalAccessError("Utility class");
    }

    public static ResponseEntity badRequestResponse(final TransactionContext tranxContext,
                                                    final String code,
                                                    final String message) {
        ApiResponse resp = generateGenericResponse(tranxContext);
        resp.setCode(BAD_REQUEST.value());
        resp.setErrors(new ArrayList<>());
        resp.getErrors().add(new ApiError());
        resp.getErrors().get(0).setCode(code);
        resp.getErrors().get(0).setMessage(message);
        JsonNode result = JsonBuilder.toJson(resp);
        return ResponseEntity
                .badRequest()
                .body(result);
    }

    public static ResponseEntity internalServerErrorResponse(final TransactionContext tranxContext,
                                                     final String code,
                                                     final String message) {
        ApiResponse resp = generateGenericResponse(tranxContext);
        resp.setCode(INTERNAL_SERVER_ERROR.value());
        resp.setErrors(new ArrayList<ApiError>());
        resp.getErrors().add(new ApiError());
        resp.getErrors().get(0).setCode(code);
        resp.getErrors().get(0).setMessage(message);
        JsonNode result = JsonBuilder.toJson(resp);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    private static ApiResponse generateGenericResponse(final TransactionContext tranxContext) {
        ApiResponse resp = new ApiResponse();
        resp.setMethod(tranxContext.getMethod());
        resp.setPath(tranxContext.getPath());
        resp.setCorrelationId(tranxContext.getCorrelationID());
        resp.setApplicationLabel(tranxContext.getApplicationLabel());
        resp.setData(JsonBuilder.parse("{}"));

        String currentXMLDateTime = DateUtils.soapTimestamp();
        resp.setTime(currentXMLDateTime);

        return resp;
    }

    public static ResponseEntity crawlerResponse(final TransactionContext tranxContext,
                                           final JsonNode configuration) {
        ApiResponse resp = generateGenericResponse(tranxContext);
        resp.setCode(OK.value());
        resp.setData(configuration);
        JsonNode nodeResponse = JsonBuilder.toJson(resp);
        return ResponseEntity.ok(nodeResponse);
    }

    public static ResponseEntity mapExceptionResponse(final TransactionContext tranxContext,
                                              final WebcrawlerException exception) {
        ApiResponse resp = generateGenericResponse(tranxContext);
        resp.setCode(exception.getCode());
        resp.setErrors(new ArrayList<>());
        resp.getErrors().add(new ApiError());
        String errorCode = String.valueOf(exception.getCode());
        String errorMessage = exception.getMessage();
        resp.getErrors().get(0).setCode(errorCode);
        resp.getErrors().get(0).setMessage(errorMessage);
        JsonNode result = JsonBuilder.toJson(resp);
        return ResponseEntity.status(exception.getCode()).body(result);
    }


}