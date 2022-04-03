package com.dianaszanto.jobsearchapi.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobErrorResponseDto {
    private String status = "error";
    private String message;

    public JobErrorResponseDto(String message) {
        this.message = message;
    }
}
