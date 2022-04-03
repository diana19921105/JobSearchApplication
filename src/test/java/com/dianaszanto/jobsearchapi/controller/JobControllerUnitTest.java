package com.dianaszanto.jobsearchapi.controller;

import com.dianaszanto.jobsearchapi.model.data.Client;
import com.dianaszanto.jobsearchapi.model.data.JobRequestDto;
import com.dianaszanto.jobsearchapi.service.impl.JobServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class JobControllerUnitTest {

    private MockMvc mockMvc;
    private JobRequestDto request;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private JobServiceImpl jobService;

    @InjectMocks
    private JobController jobController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
        request = new JobRequestDto("title", "location");
    }

    @Test
    public void givenPostPosition_whenApiKeyIsMissing_thenReturnOk() throws Exception {
        String body = (objectMapper.valueToTree(request).toString());

        Client client = new Client("name", "email@email.com", new UUID(1L, 2L));
        doNothing().when(jobService).save(any());

        mockMvc.perform(post("/positions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("x-api-key", client.getApiKey())
                                .content(body))
                .andExpect(status().isOk());

        verify(jobService).save(any());
    }
}