package com.dianaszanto.jobsearchapi.model.exception;

import javax.servlet.ServletException;

public class ClientAlreadyExistsInDatabaseException extends ServletException {
    private static final String MESSAGE = "Client already exists in the database!";

    public ClientAlreadyExistsInDatabaseException() {
        super(MESSAGE);
    }
}
