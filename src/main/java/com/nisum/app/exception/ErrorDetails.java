package com.nisum.app.exception;

import java.util.List;

public class ErrorDetails {
    private String message;
    private List<String> fieldErrors;

    public ErrorDetails() {
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(String message, List<String> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
