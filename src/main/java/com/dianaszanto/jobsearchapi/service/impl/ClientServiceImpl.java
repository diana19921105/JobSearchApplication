package com.dianaszanto.jobsearchapi.service.impl;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.repository.ClientRepository;
import com.dianaszanto.jobsearchapi.service.ClientService;
import com.dianaszanto.jobsearchapi.util.ApiKeyUtil;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(String clientName, String email) throws
                                                            ConstraintViolationException, ClientAlreadyExistsInDatabaseException {

        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        if (optionalClient.isPresent()) {
            throw new ClientAlreadyExistsInDatabaseException();
        }

        final UUID apiKey = ApiKeyUtil.generateApiKey();
        Client client = new Client(clientName, email, apiKey);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findByApiKey(UUID apiKey) {
        return clientRepository.findByApiKey(apiKey);
    }
}
