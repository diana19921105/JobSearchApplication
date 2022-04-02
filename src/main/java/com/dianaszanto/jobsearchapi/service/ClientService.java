package com.dianaszanto.jobsearchapi.service;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.exception.MissingParameterException;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client register(String clientName, String email) throws MissingParameterException;

    Optional<Client> findByApiKey(UUID apiKey);
}
