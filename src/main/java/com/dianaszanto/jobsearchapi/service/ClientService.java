package com.dianaszanto.jobsearchapi.service;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client register(String clientName, String email) throws ClientAlreadyExistsInDatabaseException;

    Optional<Client> findByApiKey(UUID apiKey);
}
