package com.dianaszanto.jobsearchapi.model.exception;

public class ClientAlreadyExistsInDatabaseException extends RuntimeException {
    private static final String MESSAGE = "Client already exists in the database!";

    public ClientAlreadyExistsInDatabaseException() {
        super(MESSAGE);
    }
}
