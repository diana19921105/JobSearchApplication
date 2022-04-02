package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.ClientErrorResponseDto;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.model.exception.MissingParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class AppControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ClientErrorResponseDto> validationExceptionHandler(ConstraintViolationException e) {
        return new ResponseEntity<>(new ClientErrorResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientAlreadyExistsInDatabaseException.class)
    ResponseEntity<ClientErrorResponseDto> validationExceptionHandler(ClientAlreadyExistsInDatabaseException e) {
        return new ResponseEntity<>(new ClientErrorResponseDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MissingParameterException.class)
    ResponseEntity<ClientErrorResponseDto> missingParameterExceptionHandler(MissingParameterException e) {
        return new ResponseEntity<>(new ClientErrorResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
