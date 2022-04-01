package com.dianaszanto.jobsearchapi.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClientSuccessResponseDto {
    private String status = "ok";
    private UUID apiKey;

    public ClientSuccessResponseDto(UUID apiKey) {
        this.apiKey = apiKey;
    }
}
