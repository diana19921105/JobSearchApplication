package com.dianaszanto.jobsearchapi.service;

import com.dianaszanto.jobsearchapi.model.data.Client;

public interface ClientService {
    Client register(String clientName, String email);
}
