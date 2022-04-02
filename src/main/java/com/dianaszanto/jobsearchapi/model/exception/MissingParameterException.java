package com.dianaszanto.jobsearchapi.model.exception;

public class MissingParameterException extends IllegalAccessException {
    private static final String status = "error";

    public MissingParameterException(String message) {
        super(message);
    }
}
