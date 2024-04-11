package com.devsuperior.dscatalog.adapters.controller.advice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputResponse {

    private LocalDateTime timestamp;
    private Integer status;

    private String error;

    private String message;

    private String path;

    private List<String> globalErrorsMessage = new ArrayList<>();

    private List<FieldErrorsOutputResponse> fieldErrors = new ArrayList<>();

    public ValidationErrorsOutputResponse(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public void addError(String message) {
        globalErrorsMessage.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorsOutputResponse fieldError = new FieldErrorsOutputResponse(field, message);
        fieldErrors.add(fieldError);
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getGlobalErrorsMessage() {
        return globalErrorsMessage;
    }

    public List<FieldErrorsOutputResponse> getFieldErrors() {
        return fieldErrors;
    }
}
