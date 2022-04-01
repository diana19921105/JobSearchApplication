package com.dianaszanto.jobsearchapi.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientErrorResponseDto {
    private String status = "error";
    private String message;

    public ClientErrorResponseDto(String message) {
        this.message = message;
    }
}
