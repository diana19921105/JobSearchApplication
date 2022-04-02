package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.data.ClientRequestDto;
import com.dianaszanto.jobsearchapi.model.data.ClientSuccessResponseDto;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.model.exception.MissingParameterException;
import com.dianaszanto.jobsearchapi.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@RestController
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> register(@RequestBody ClientRequestDto clientRequestDto)
            throws ClientAlreadyExistsInDatabaseException, ConstraintViolationException, MissingParameterException {
        Client savedClient = clientService.register(clientRequestDto.getName(), clientRequestDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new ClientSuccessResponseDto(savedClient.getApiKey()));
    }
}
