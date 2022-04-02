package com.dianaszanto.jobsearchapi.unit;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.model.exception.MissingParameterException;
import com.dianaszanto.jobsearchapi.repository.ClientRepository;
import com.dianaszanto.jobsearchapi.service.impl.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceUnitTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void givenFindByApiKey_whenClientExists_thenReturnValidClient() throws MissingParameterException {
        when(clientRepository.findByEmail(getClient().getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(any())).thenReturn(Optional.of(getClient()).get());

        Client expected = new Client("name", "email", new UUID(1L, 2L));

        assertEquals(expected, clientService.register(getClient().getName(), getClient().getEmail()));
        verify(clientRepository, times(1)).findByEmail(anyString());
        verify(clientRepository, times(1)).save(any());
    }

    @Test(expected = ClientAlreadyExistsInDatabaseException.class)
    public void givenRegister_whenClientAlreadyExists_thenReturnException() throws MissingParameterException {
        Client client = new Client("name", "email", new UUID(1L, 2L));
        when(clientRepository.findByEmail("email")).thenReturn(Optional.of(getClient()));

        clientService.register(client.getName(), client.getEmail());
    }

    private Client getClient() {
        return new Client("name", "email", new UUID(1L, 2L));
    }

    @Test(expected = MissingParameterException.class)
    public void givenRegister_whenEmptyField_thenThrowsMissingParameterException()
            throws MissingParameterException {
        clientService.register(null, "email");
        clientService.register("client", null);
        clientService.register("client1", "email1");
        clientService.register(null, null);
        clientService.register("username", null);
    }
}