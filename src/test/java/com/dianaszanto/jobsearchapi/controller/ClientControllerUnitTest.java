package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.data.ClientErrorResponseDto;
import com.dianaszanto.jobsearchapi.model.exception.ClientAlreadyExistsInDatabaseException;
import com.dianaszanto.jobsearchapi.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientControllerUnitTest {


    ClientServiceImpl mockedClientService = Mockito.mock(ClientServiceImpl.class);
    ClientController controller = new ClientController(mockedClientService);
    Client mockedClient = new Client();
    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void register() throws Exception {
        ClientErrorResponseDto errorResponse = new ClientErrorResponseDto("Client already exists");
        when(mockedClientService.register(mockedClient.getName(), mockedClient.getEmail())).thenThrow(ClientAlreadyExistsInDatabaseException.class);

        mockMvc.perform(post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(mockedClient)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(errorResponse.getStatus())))
                .andExpect(jsonPath("$.message", is(errorResponse.getMessage())))
                .andDo(print());
    }
}