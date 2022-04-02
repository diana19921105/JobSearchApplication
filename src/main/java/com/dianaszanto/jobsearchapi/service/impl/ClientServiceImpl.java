package com.dianaszanto.jobsearchapi.service.impl;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.model.exception.MissingParameterException;
import com.dianaszanto.jobsearchapi.repository.ClientRepository;
import com.dianaszanto.jobsearchapi.service.ClientService;
import com.dianaszanto.jobsearchapi.util.ApiKeyUtil;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client register(String clientName, String email) throws ClientAlreadyExistsInDatabaseException,
                                                                   ConstraintViolationException, MissingParameterException {
        Map<String, String> missingFields = setUpValidate(clientName, email);
        validate(missingFields);

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

    private Map<String, String> setUpValidate(String clientName, String email) {
        Map<String, String> missingFields = new HashMap<>();
        missingFields.put("clientName", clientName);
        missingFields.put("email", email);
        return missingFields;
    }

    private void validate(Map<String, String> fields) throws MissingParameterException {
        List<String> missingFields = new ArrayList<>();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isBlank()) {
                missingFields.add(entry.getKey());
            }
        }
        if (!missingFields.isEmpty()) {
            String joined = String.join(", ", missingFields);
            throw new MissingParameterException("Missing parameter(s): " + joined);
        }
    }
}
