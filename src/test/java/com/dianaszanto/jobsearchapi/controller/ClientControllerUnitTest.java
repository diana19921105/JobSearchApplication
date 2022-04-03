package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.data.ClientErrorResponseDto;
import com.dianaszanto.jobsearchapi.model.data.ClientRequestDto;
import com.dianaszanto.jobsearchapi.model.data.ClientSuccessResponseDto;
import com.dianaszanto.jobsearchapi.model.data.JobRequestDto;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ClientControllerUnitTest {
    private MockMvc mockMvc;
    private ClientRequestDto requestDto;

    @Mock
    private ClientServiceImpl service;

    @InjectMocks
    private ClientController clientController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        requestDto = new ClientRequestDto("name", "email@email.com");
    }

    @Test(expected = ClientAlreadyExistsInDatabaseException.class)
    public void givenRegister_whenClientAlreadyExists_thenReturnException() throws Exception {
        ClientErrorResponseDto errorResponse = new ClientErrorResponseDto("Client already exists in the database!");
        String request = objectMapper.valueToTree(requestDto).toString();

        doThrow(new ClientAlreadyExistsInDatabaseException()).when(service).register(anyString(), anyString());

        mockMvc.perform(post("/clients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(errorResponse.getStatus())))
                .andExpect(jsonPath("$.message", is(errorResponse.getMessage())))
                .andDo(print());
    }

    @Test
    public void givenRegister_whenClientNotExists_thenReturnOk() throws Exception {
        Client client = new Client("name2", "email@email.com", new UUID(1L, 3L));
        String request = objectMapper.valueToTree(client).toString();

        when(service.register(client.getName(), client.getEmail())).thenReturn(client);

        mockMvc.perform(post("/clients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isOk())
                .andDo(print());
    }
}